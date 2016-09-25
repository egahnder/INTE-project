package org.pucko.CommandProcessors;

import org.pucko.commands.Command;
import org.pucko.commands.CommandArguments;
import org.pucko.commands.CommandUtils.UtilsBuilder;
import org.pucko.core.CommandFactory;
import org.pucko.core.InputHandler;
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
    public ArrayList<Command> process(String command, CommandArguments commandArguments) {
        ArrayList<Command> commands = new ArrayList<>();
        ArrayList<String> args = splitCommand(command);
        UtilsBuilder utilsBuilder = getUtilsBuilder(commandArguments, args);
        commands.add(commandFactory.createCommand(args.get(0), utilsBuilder.build()));
        return commands;
    }
}
