package org.pucko.commands;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.pucko.core.OutputHandler;
import org.pucko.core.WorkingDirectory;

public class Cd extends Command {
    private Path newPath;
    private Path oldPath = getWorkingDirectory();
    


    public Cd(ArrayList<String> args, WorkingDirectory wd, OutputHandler oh) {
        super(args, wd, oh);
      
    }

    public boolean execute() {
        
        // Then we change the path using WorkingDirectorys changePath().
        resolveNewPath();
        setWorkingDirectory(newPath);
        return true;
    }
    
    private void resolveNewPath() {
        
        // If the argument is ".." create the new Path by calling getParent() on the old path
        // If the argument is "~" create a Path to user home
        // Otherwise create the newPath by sticking the new Path from the args ArrayList onto the oldPath with resolve
        
        if (getArg(0).equals("..")) {
            newPath = oldPath.getParent();
        } else if (getArg(0).equals("~")) {
            String homePath = System.getProperty("user.home");
            newPath = Paths.get(homePath);
        } else  {
            newPath = oldPath.resolve(Paths.get(getArg(0)));
        
        }
    }

    @Override
    protected boolean verifyUndoable() {
        return false;
    }

    @Override
    protected boolean verifyExecutable() {

        // If args contains null, return false
        if (getArg(0).contains(null)) {
            return false;
        }
        
        // Lets resolve the new Path and set newPath
        resolveNewPath();
        
       
        //Lets make sure the directory exists
        if (!Files.exists(newPath)) {
            return false;
        }
        
      //Lets make sure the directory is readable
        if (!Files.isReadable(newPath)) {
            return false;
        }
       
        // If all tests pass, this command is validated
        return true;
    }

    @Override
    protected boolean undo() {
        return false;
    }


}
