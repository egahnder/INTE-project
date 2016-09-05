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
        String oldPath = wd.getPath().toString();
        String newPath = oldPath + "/" + args.get(0);
        Path replacementPath = Paths.get(newPath);
        wd.changePath(replacementPath);
        return true;
    }

}
