package org.pucko.commands;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.pucko.core.InputHandler;
import org.pucko.core.OutputHandler;
import org.pucko.core.WorkingDirectory;

public class Cd extends Command {
    private Path newPath = Paths.get("/");
    private final Path oldPath = getWorkingDirectory();
    private String commando;

    /**
     * @param commandUtils Utils used for IO operations, command arguments, working directory etc.
     */
    public Cd(CommandUtils commandUtils) {
        super(commandUtils);
    }


    public boolean execute() {

        setWorkingDirectory(newPath);

        return true;
    }

    private void resolveNewPath() {

        // If the argument is ".." create the new Path by calling getParent() on the old path
        // If the argument is "~" create a Path to user home
        // Otherwise create the newPath by sticking the new Path from the args ArrayList onto the oldPath with resolve
        if (getArgs().size() == 1) {
            newPath = Paths.get(System.getProperty("user.home"));
        } else {

            commando = getArg(1);

            if ("..".equals(commando)) {
                newPath = oldPath.getParent();
            } else if (".".equals(commando)) {
                parsePeriod(commando);
            } else if (commando.startsWith("~")) {
                parseTilde();
            } else if (commando.startsWith("/")) {
                parseSlash(commando);
            } else {
                newPath = oldPath.resolve(Paths.get(commando));

            }
        }
    }

    private void parsePeriod( String input) {
        newPath = oldPath.normalize();
        if (input.length() > 1) {
            newPath = oldPath.resolve(Paths.get(input));
        }
    }

    private void parseTilde() {
        newPath = Paths.get(System.getProperty("user.home"));
        if (commando.length() > 1) {
            String[] splitCommandos = commando.split("~");
            if (splitCommandos[1].startsWith("/")) {
                parseSlash(splitCommandos[1]);
            } else if (splitCommandos[1].startsWith(".")) {
                parsePeriod(splitCommandos[1]);
            }
        }
    }

    private void parseSlash(String input) {
        if (input.length() > 1) {
            newPath = newPath.resolve(Paths.get(input.substring(1)));
        }
        newPath = newPath.resolve(Paths.get(input));

    }

    @Override
    protected boolean verifyUndoable() {
        return false;
    }

    @Override
    protected boolean verifyExecutable() {

        // If args is null, return false

        if (getArgs().contains(null)) {
            error("cd: Invalid Argument");
            return false;
        }

        resolveNewPath();

        // Lets make sure the path exists
        if (newPath == null) {
            error("cd: No such file or directory");
            return false;

        }

        //Lets make sure the directory exists
        if (!Files.exists(newPath)) {
            error("cd: No such file or directory: " +getArg(1));
            return false;
        }

        //Lets make sure the directory is readable
        if (!Files.isReadable(newPath)) {
            error("cd: You do not have permission to access this directory");
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
