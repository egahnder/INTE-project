package org.pucko.commands;

import java.util.ArrayList;

public class Echo extends Command {


    /**
     * @param commandUtils Utils used for IO operations, command arguments, working directory etc.
     */
    public Echo(CommandUtils commandUtils) {
        super(commandUtils);
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
    public boolean verifyExecutable() {


        return getArgs() != null && getArgs().size() != 0;

    }

}