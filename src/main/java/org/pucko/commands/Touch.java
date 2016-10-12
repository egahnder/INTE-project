package org.pucko.commands;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Touch extends Command {


    public Touch(CommandUtils commandUtils) {
        super(commandUtils);
    }

    @Override
    public boolean execute() {

        List<String> args = getArgs().subList(1, getArgs().size());

        for (String s : args) {

            try {
                Files.createFile(getWorkingDirectory().resolve(Paths.get(s)));
            } catch (FileAlreadyExistsException e) {
                error("File already exists");
                return false;
            } catch (IOException e) {
                error("Illegal filename");
            }
        }
        return true;
    }

    @Override
    protected boolean verifyExecutable() {

        if (getArgs().size() < 2) {
            error("Filename is missing");
            return false;
        } else if (!Files.isReadable(getWorkingDirectory()) || !Files.isWritable(getWorkingDirectory())) {
            error("touch: can not make 'touch' on <<"+getArg(1) +">>: permission denied");
            return false;
        }
        return true;

    }

    @Override
    protected boolean undo() {
        return false;
    }

    @Override
    protected boolean verifyUndoable() {
        return false;
    }


}
