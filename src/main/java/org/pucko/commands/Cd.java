package org.pucko.commands;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.pucko.core.WorkingDirectory;

public class Cd extends Command {


    public Cd(ArrayList<String> args, WorkingDirectory wd) {
        super(args, wd);
      
    }

    public boolean execute() {
        Path newPath;
        

        // Lets get the old Path
        Path oldPath = workingDirectory.getPath();

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
        
        //Lets make sure the directory exists
        boolean isRegularReadableFile = Files.isReadable(newPath);
        
        if (!isRegularReadableFile) {
            setValid(false);
            return false;
        }

        // Then we change the path using WorkingDirectorys changePath().
        workingDirectory.changePath(newPath);
        return true;
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean valiadate() {
        // TODO Auto-generated method stub
        return false;
    }

}
