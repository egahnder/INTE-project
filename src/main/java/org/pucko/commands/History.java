package org.pucko.commands;

import com.google.common.collect.ImmutableList;
import org.pucko.core.InputHandler;
import org.pucko.core.OutputHandler;
import org.pucko.core.WorkingDirectory;

import java.util.ArrayList;

/**
 * Created by Tobias on 20/09/16.
 */
public class History extends Command {

    private boolean printCompleteHistory;
    private int commandNumber;

    /**
     * @param commandUtils Utils used for IO operations, command arguments, working directory etc.
     */
    public History(CommandUtils commandUtils) {
        super(commandUtils);
    }

    @Override
    protected boolean execute() {
        int commandIndex = 1;
        ImmutableList commandHistory = getHistory();

        if (printCompleteHistory) {
            commandNumber = commandHistory.size() - 1;
        }

        for (int i = 0; i < commandNumber; i++) {
            output(commandIndex + "\t" + commandHistory.get(i));
            commandIndex++;
        }
        return true;

    }

    @Override
    protected boolean verifyExecutable() {

        int historySize = getHistory().size();

        if (historySize == 1) {
            error("No command history available");
            return false;
        }

        int argsSize = getArgs().size();
        if (argsSize == 1) {
            printCompleteHistory = true;
            return true;
        }

        if (argsSize == 2) {

            try {
                commandNumber = Integer.parseInt(getArg(1));
            } catch (NumberFormatException e) {
                error("Only numbers are accepted");
                return false;
            }

            if (commandNumber > getHistory().size()) {
                error("Number greater than command history");
                return false;
            }

            if (commandNumber < 1) {
                error("Command number has to be > 0");
                return false;
            }
            return true;
        }

        error("Too many arguments");
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
