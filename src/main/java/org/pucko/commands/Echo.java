package org.pucko.commands;

import java.util.ArrayList;

import org.pucko.core.OutputHandler;
import org.pucko.core.WorkingDirectory;

public class Echo extends Command {
    private ArrayList<String> localArgs;

    public Echo(ArrayList<String> args, WorkingDirectory wd, OutputHandler oh, OutputHandler eh) {
        super(args, wd, oh, eh);
        localArgs = args;
    }

    @Override
    public boolean execute() {
        String outputString = "";
        for (String s : localArgs) {
            outputString += s + " ";
        }
        outputString = outputString.trim();
        output(outputString);
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

    @Override
    public boolean verifyExecutable() {

        if (localArgs != null && localArgs.size() != 0) {
            return true;
        }

        return false;
    }

}