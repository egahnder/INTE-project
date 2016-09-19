package org.pucko.commands;

import java.util.ArrayList;

import org.pucko.core.OutputHandler;
import org.pucko.core.WorkingDirectory;

public class Echo extends Command {

    public Echo(ArrayList<String> args, WorkingDirectory wd, OutputHandler oh, OutputHandler eh) {
        super(args, wd, oh, eh);
    }

    @Override
    public boolean execute() {
        StringBuilder sb = new StringBuilder();
        ArrayList<String> args = getArgs();
        args.remove(0);
        for (String s : args) {
            sb.append(s);
            sb.append(" ");
        }
        output(sb.toString().trim());
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


        return getArgs() != null && getArgs().size() != 0;

    }

}