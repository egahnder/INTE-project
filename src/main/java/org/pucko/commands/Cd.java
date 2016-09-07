package org.pucko.commands;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.pucko.core.WorkingDirectory;

public class Cd extends Command {
    private Path newPath;
    private Path oldPath = workingDirectory.getPath();
    


    public Cd(ArrayList<String> args, WorkingDirectory wd) {
        super(args, wd);
      
    }

    public boolean execute() {
        validate();
       
        // Then we change the path using WorkingDirectorys changePath().
        workingDirectory.changePath(newPath);
        return true;
    }
    
    private void resolveNewPath() {
        
        // If the argument is ".." create the new Path by calling getParent() on the old path
        // If the argument is "~" create a Path to user home
        // Otherwise create the newPath by sticking the new Path from the args ArrayList onto the oldPath with resolve
        
        if (args.get(0).equals("..")) {
            newPath = workingDirectory.getPath().getParent();
        } else if (args.get(0).equals("~")) {
            String homePath = System.getProperty("user.home");
            newPath = Paths.get(homePath);
        } else  {
            newPath = oldPath.resolve(Paths.get(args.get(0)));
        
        }
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean validate() {

        // If args contains null, return false
        if (args.contains(null)) {
            valid = false;
            return false;
        }
        
        // Lets resolve the new Path and set newPath
        resolveNewPath();
        
       
        //Lets make sure the directory exists
        if (!Files.exists(newPath)) {
            setValid(false);
            return false;
        }
        
      //Lets make sure the directory is readable
        if (!Files.isReadable(newPath)) {
            setValid(false);
            return false;
        }
       
        // If all tests pass, this command is validated
        setValid(true);
        return true;
    }
    

}
