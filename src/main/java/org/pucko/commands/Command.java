package org.pucko.commands;

import java.nio.file.Path;
import java.util.ArrayList;

import com.google.common.collect.ImmutableList;
import org.pucko.core.OutputHandler;

public abstract class Command implements OutputHandler {

    private CommandUtils commandUtils;
    private boolean validForExecute;
    private boolean executable;


    /**
     * @param commandUtils Utils used for IO operations, command arguments, working directory etc.
     */
    public Command(CommandUtils commandUtils) {
        this.commandUtils = commandUtils;
    }


    /**
     * Execute the logic of the implementing command.
     * The implementor must ensure that all checked exceptions are handled.
     *
     * @return true if method was run without any exceptions, otherwise false.
     */
    protected abstract boolean execute();

    /**
     * Runs all the checks needed to ensure that the command can be properly run.
     * The implementor must ensure that all checked exceptions are handled.
     *
     * @return true if the command can be executed properly, otherwise false.
     */
    protected abstract boolean verifyExecutable();

    /**
     * Undoes the logic of the executed command.
     * The implementor must ensure that all checked exceptions are handled.
     *
     * @return true if method was run without any exceptions, otherwise false.
     */


    /**
     * @return true if the command was executed properly. Returns false if there was an error running the command or if
     * the command was invalid.
     */
    public boolean runCommand() {
        if (!validForExecute) {
            validateForExecution();
        }
        return executable && execute();
    }


    /**
     * Appends argument to command. Invalidates for undo and execution.
     *
     * @param output String that should be appended to the commands arguments.
     */
    @Override
    public void handleOutput(String output) {
        commandUtils.addArg(output);
        invalidateForExecution();
    }

    protected final ArrayList<String> getArgs() {
        ArrayList<String> newList = new ArrayList<>();
        if (commandUtils.getArgs() != null) {
            newList.addAll(commandUtils.getArgs());
        }

        return newList;

    }

    /**
     * returns argument for a Command
     *
     * @param index index of command argument.
     * @return argument for command.
     */
    protected final String getArg(int index) {
        return commandUtils.getArgs().get(index);
    }

    protected final ImmutableList getHistory() {
        return commandUtils.getHistory();
    }

    /**
     * Changes the path of the working directory
     *
     * @param newPath The new path for the working directory.
     */
    protected final void setWorkingDirectory(Path newPath) {
        commandUtils.changeWorkingDirectory(newPath);
        invalidateForExecution();
    }

    /**
     * @return Path set in working directory.
     */
    protected final Path getWorkingDirectory() {
        return commandUtils.getWorkingDirectory();
    }

    /**
     * Used to send out output generated in Command
     *
     * @param output Output to send out
     */
    protected final void output(String output) {
        commandUtils.output(output);
    }

    /**
     * Used to send out errors generated in Command.
     *
     * @param error Error to send out
     */
    protected final void error(String error) {
        commandUtils.error(error);
    }

    /**
     * Validated that command can be executed.
     */
    private void validateForExecution() {
        executable = verifyExecutable();
        validForExecute = true;
    }

    /**
     * Invalidates command from being executed.
     */
    private void invalidateForExecution() {
        validForExecute = false;
    }


}

