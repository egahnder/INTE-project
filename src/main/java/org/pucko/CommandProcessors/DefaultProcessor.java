package org.pucko.CommandProcessors;

import org.pucko.commands.Command;
import org.pucko.core.CommandFactory;
import org.pucko.core.OutputHandler;
import org.pucko.core.WorkingDirectory;

import java.util.ArrayList;

/**
 * Created by eric on 2016-09-16.
 */
public class DefaultProcessor extends CommandProcessor {
    public DefaultProcessor(CommandFactory commandFactory) {
        super(commandFactory);
    }

    @Override
    public ArrayList<Command> process(String command, WorkingDirectory workingDirectory, OutputHandler outputHandler) {
        ArrayList<Command> commands = new ArrayList<>();
        commands.add(createCommandFromString(command, workingDirectory, outputHandler));
        return commands;
    }
}
