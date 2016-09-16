package org.pucko.CommandProcessors;


import org.pucko.commands.Command;
import org.pucko.core.CommandFactory;
import org.pucko.core.OutputHandler;
import org.pucko.core.WorkingDirectory;

import java.util.ArrayList;
import java.util.Arrays;

public class PipeProcessor extends CommandProcessor {

    public PipeProcessor(CommandFactory commandFactory) {
        super(commandFactory);
    }

    @Override
    public ArrayList<Command> process(String command, WorkingDirectory workingDirectory, OutputHandler outputHandler) {
        ArrayList<Command> commands = new ArrayList<>();
        if(command.matches("[^(\\s\\|)]+( [^(\\s\\|)]+)* \\| [^(\\s\\|)]+( [^(\\s\\|)]+)*( \\| [^(\\s\\|)]+( [^(\\s\\|)]+)*)*")){
            String[] splitCommnds = command.split(" \\| ");
            Command commandToPipeTo = createCommandFromString(splitCommnds[0], workingDirectory, outputHandler);
            commands.add(commandToPipeTo);
            splitCommnds = Arrays.copyOfRange(splitCommnds, 1, splitCommnds.length);
            for (String stringCommand : splitCommnds) {
                Command newCommand = createCommandFromString(stringCommand, workingDirectory, commandToPipeTo);
                commandToPipeTo = newCommand;
                commands.add(newCommand);
            }
        }
        else{
            commands = sendToNextProcessor(command, workingDirectory, outputHandler);
        }
        return commands;
    }
}
