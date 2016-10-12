package org.pucko.commands;

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


}
