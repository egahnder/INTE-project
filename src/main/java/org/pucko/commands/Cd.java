package org.pucko.commands;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.pucko.core.WorkingDirectory;

public class Cd {
    private ArrayList<String> args;
    private WorkingDirectory wd;

    public Cd(ArrayList<String> args, WorkingDirectory wd) {
        this.args = args;
        this.wd = wd;
    }
    
    public boolean execute() {
        
        // Lets get the old Path
        Path oldPath = wd.getPath();
        
        //Them create the newPath by sticking the new Path from the args ArrayList onto the oldPath with resolve
        Path newPath = oldPath.resolve(Paths.get(args.get(0)));
        
        // Then we change the path using WorkingDirectorys changePath().
        wd.changePath(newPath);
        return true;
    }

}
