package org.pucko.commands;

import org.pucko.core.OutputHandler;
import org.pucko.core.WorkingDirectory;

import java.util.ArrayList;

/**
 * Created by loxtank on 12/09/16.
 */
public class DefaultCommand extends Command {

    public DefaultCommand(ArrayList<String> args, WorkingDirectory wd, OutputHandler oh) {
        super(args, wd, oh);
    }
    @Override
    public boolean execute() {
        return false;
    }

    @Override
    public void undo() {

    }

    @Override
    public boolean validate() {
        return false;
    }
}
