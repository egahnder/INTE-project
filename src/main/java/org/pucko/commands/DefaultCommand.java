package org.pucko.commands;

import org.pucko.core.InputHandler;
import org.pucko.core.OutputHandler;
import org.pucko.core.WorkingDirectory;

import java.util.ArrayList;

public class DefaultCommand extends Command {


    /**
     * @param commandUtils Utils used for IO operations, command arguments, working directory etc.
     */
    public DefaultCommand(CommandUtils commandUtils) {
        super(commandUtils);
    }

    @Override
    public boolean execute() {
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
