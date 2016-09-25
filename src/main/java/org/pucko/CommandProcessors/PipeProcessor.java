package org.pucko.CommandProcessors;


import org.pucko.commands.Command;
import org.pucko.commands.CommandArguments;
import org.pucko.commands.CommandUtils.UtilsBuilder;
import org.pucko.core.CommandFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PipeProcessor extends CommandProcessor {

    public PipeProcessor(CommandFactory commandFactory) {
        super(commandFactory);
    }

    @Override
    public ArrayList<Command> process(String command, CommandArguments commandArguments) {
        ArrayList<Command> commands = new ArrayList<>();
        if(command.matches("[^(\\s\\|)]+( [^(\\s\\|)]+)* \\| [^(\\s\\|)]+( [^(\\s\\|)]+)*( \\| [^(\\s\\|)]+( [^(\\s\\|)]+)*)*")){
            String[] splitCommands = command.split(" \\| ");
            ArrayList<String> args = splitCommand(splitCommands[0]);
            UtilsBuilder utilsBuilder = getUtilsBuilder(commandArguments, args);
            Command commandToPipeTo = commandFactory.createCommand(splitCommands[0], utilsBuilder.build() );
            commands.add(commandToPipeTo);
            splitCommands = Arrays.copyOfRange(splitCommands, 1, splitCommands.length);
            for (String stringCommand : splitCommands) {
                args = splitCommand(stringCommand);
                utilsBuilder = getUtilsBuilder(commandArguments, args);
                utilsBuilder.addOutputHandler(commandToPipeTo);
                Command newCommand = commandFactory.createCommand(args.get(0), utilsBuilder.build());
                commandToPipeTo = newCommand;
                commands.add(newCommand);
                Collections.reverse(commands);
            }
        }
        else{
            commands = sendToNextProcessor(command, commandArguments);
        }
        return commands;
    }
}
