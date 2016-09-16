package org.pucko.CommandProcessors;


import org.pucko.commands.Command;
import org.pucko.core.CommandFactory;
import org.pucko.core.OutputHandler;
import org.pucko.core.WorkingDirectory;

import java.util.ArrayList;

public class PipeProcessor extends CommandProcessor {

    public PipeProcessor(CommandFactory commandFactory) {
        super(commandFactory);
    }

    @Override
    public ArrayList<Command> process(String command, WorkingDirectory workingDirectory, OutputHandler outputHandler) {
        ArrayList<Command> commands = new ArrayList<>();
        if(command.matches("[^(\\s\\|)]+( [^(\\s\\|)]+)* \\| [^(\\s\\|)]+( [^(\\s\\|)]+)*( \\| [^(\\s\\|)]+( [^(\\s\\|)]+)*)*")){
            String[] splitCommnds = command.split(" \\| ");
            for (String stringCommand : splitCommnds) {
                Command newCommand = createCommandsFromStrings(stringCommand, workingDirectory, outputHandler);
                commands.add(newCommand);
            }

        }
        return commands;
    }
}
