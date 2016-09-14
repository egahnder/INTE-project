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


    public Cd(ArrayList<String> args, WorkingDirectory workingDirectory, OutputHandler outputHandler) {
        super(args, workingDirectory, outputHandler);

    }

    public Cd(ArrayList<String> args, WorkingDirectory workingDirectory, OutputHandler outputHandler, OutputHandler errorHandler) {
        super(args, workingDirectory, outputHandler, errorHandler);
    }

    public boolean execute() {

        setWorkingDirectory(newPath);

        return true;
    }

    private void resolveNewPath() {

        // If the argument is ".." create the new Path by calling getParent() on the old path
        // If the argument is "~" create a Path to user home
        // Otherwise create the newPath by sticking the new Path from the args ArrayList onto the oldPath with resolve

        if (getArg(0).equals("..")) {
            newPath = oldPath.getParent();
        } else if (getArg(0).equals(".")) {
            newPath = oldPath;
        } else if (getArg(0).equals("~")) {
            String homePath = System.getProperty("user.home");
            newPath = Paths.get(homePath);
        } else if (getArg(0).equals("/")) {
            newPath = Paths.get("/");
        } else {
            newPath = oldPath.resolve(Paths.get(getArg(0)));

        }
    }

    @Override
    protected boolean verifyUndoable() {
        return false;
    }

    @Override
    protected boolean verifyExecutable() {

        // If args is null, return false
        try {
            getArg(0);
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            error("ERROR: No argument provided");
            return false;
        }

        if (getArg(0) == null) {
            error("ERROR: No argument provided");
            return false;
        }

        resolveNewPath();

        // Lets make sure the path exists
        if (newPath == null) {
            error("ERROR: Directory does not exist");
            return false;

        }

        //Lets make sure the directory exists
        if (!Files.exists(newPath)) {
            error("ERROR: Directory does not exist");
            return false;
        }

        //Lets make sure the directory is readable
        if (!Files.isReadable(newPath)) {
            error("ERROR: You do not have permission to acces this directory");
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
