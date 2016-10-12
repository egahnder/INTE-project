package org.pucko.commands;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Cd extends Command {
    private Path newPath = Paths.get("/");
    private final Path oldPath = getWorkingDirectory();
    private String commando;

    public Cd(CommandUtils commandUtils) {
        super(commandUtils);
    }


    public boolean execute() {

        setWorkingDirectory(newPath);

        return true;
    }

    private void resolveNewPath() {


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

        if (getArgs().contains(null)) {
            error("cd: Invalid Argument");
            return false;
        }

        resolveNewPath();

        if (newPath == null) {
            error("cd: No such file or directory");
            return false;
        }

        if (!Files.exists(newPath)) {
            error("cd: No such file or directory: " +getArg(1));
            return false;
        }

        if (!Files.isReadable(newPath)) {
            error("cd: You do not have permission to access this directory");
            return false;
        }

        return true;
    }

    @Override
    protected boolean undo() {
        return false;
    }


}
