package org.pucko.CommandProcessors;


import org.pucko.commands.Command;
import org.pucko.commands.CommandArguments;
import org.pucko.commands.CommandUtils.UtilsBuilder;
import org.pucko.commands.UtilsBuilderFactory;
import org.pucko.core.CommandFactory;
import org.pucko.core.OutputHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.regex.Pattern;

public class PipeProcessor extends CommandProcessor {


    public PipeProcessor(CommandFactory commandFactory, UtilsBuilderFactory utilsBuilderFactory) {
        super(commandFactory, utilsBuilderFactory);
    }

    @Override
    public ArrayList<Command> process(String command, CommandArguments commandArguments) {
        ArrayList<Command> commands = new ArrayList<>();
        if(command.matches(pipedCommandPattern().pattern())){
            ArrayList<String> commandsList = splitPipedCommands(command);
            OutputHandler outputHandler = commandArguments.getOutputHandler();
            Iterator<String> iter = commandsList.iterator();
            while (iter.hasNext()){
                String nextString = iter.next();
                iter.remove();
                Command nextCommand = getCommand(commandArguments, outputHandler, nextString);
                outputHandler = nextCommand;
                commands.add(nextCommand);
                Collections.reverse(commands);
            }
        }
        else{
            commands = sendToNextProcessor(command, commandArguments);
        }
        return commands;
    }

    private Command getCommand(CommandArguments commandArguments, OutputHandler outputHandler, String nextCommand) {
        ArrayList<String> args = splitCommand(nextCommand);
        UtilsBuilder utilsBuilder = getUtilsBuilder(commandArguments, args);
        utilsBuilder.addOutputHandler(outputHandler);
        return commandFactory.createCommand(args.get(0), utilsBuilder.build());
    }


    private Pattern pipedCommandPattern(){
        return Pattern.compile("[^(\\s\\|)]+( [^(\\s\\|)]+)* \\| [^(\\s\\|)]+( [^(\\s\\|)]+)*( \\| [^(\\s\\|)]+( [^(\\s\\|)]+)*)*");
    }

    private ArrayList<String> splitPipedCommands(String pipedCommand){
        String[] commands = pipedCommand.split(" \\| ");
        return new ArrayList<>(Arrays.asList(commands));
    }
}
