
package org.pucko.commands;

/**
 * Created by Tobias on 15/09/16.
 */

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


import org.pucko.core.OutputHandler;
import org.pucko.core.WorkingDirectory;

public class Touch extends Command {


    public Touch(ArrayList<String> args, WorkingDirectory workingDirectory, OutputHandler outputHandler, OutputHandler errorHandler) {
        super(args, workingDirectory, outputHandler, errorHandler);
    }

    @Override
    public boolean execute() {

        Path currentDir = getWorkingDirectory();
        Path newPath1 = Paths.get(getArg(0));
        Path newPath2 = currentDir.resolve(newPath1);

        try {
            Files.createFile(newPath2);
        } catch (FileAlreadyExistsException e) {
            output("File already exist.");
        } catch (IOException e) {
        }

        return false;
    }

    @Override
    protected boolean verifyExecutable() {
        return false;
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
