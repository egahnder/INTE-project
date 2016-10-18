package org.pucko.commands;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Cd extends Command {
    private Path newPath = Paths.get("/");
    private final Path oldPath = getWorkingDirectory();

    public Cd(CommandUtils commandUtils) {
        super(commandUtils);
    }


    public boolean execute() {

        setWorkingDirectory(newPath);

        return true;
    }

    private void resolveNewPath() {
        ArrayList<String> arguments = getArgs();
        if (arguments.size() == 1) {
            newPath = Paths.get(System.getProperty("user.home"));
            return;

        }

        String argument = arguments.get(1);

        if (arguments.get(1).startsWith("~")) {
            argument = arguments.get(1).replaceFirst("~", System.getProperty("user.home"));
        }

        newPath = oldPath.resolve(Paths.get(argument)).normalize();
    }


    @Override
    protected boolean verifyExecutable() {

        resolveNewPath();

        if (!Files.exists(newPath)) {
            error("cd: No such file or directory: " + getArgs().get(1));
            return false;
        }

        if (!Files.isReadable(newPath)) {
            error("cd: You do not have permission to access this directory");
            return false;
        }

        return true;
    }


}
