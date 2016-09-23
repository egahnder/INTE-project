package org.pucko.core;

import org.pucko.CommandProcessors.CommandProcessor;
import org.pucko.commands.Command;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandParser {
    private final CommandProcessor commandProcessor;

    public CommandParser(CommandProcessor commandProcessor){
        this.commandProcessor = commandProcessor;
    }

    public ArrayList<Command> parseCommands(String command, WorkingDirectory workingDirectory, OutputHandler outputHandler, InputHandler inputHandler){
        ArrayList<String> commandStrings = splitMultipleCommands(command);
        ArrayList<Command> commands = new ArrayList<>();
        for(String commandString : commandStrings){
            commands.addAll(commandProcessor.process(commandString, workingDirectory, outputHandler, inputHandler));
        }

        return commands;
    }

    private ArrayList<String> splitMultipleCommands(String command) {
        ArrayList<String> commandStrings = new ArrayList<>();
        if (command.matches("[^\\s&]+( [^(\\s&)]+)* && [^\\s&]+( [^(\\s&)]+)*( && [^\\s&]+( [^(\\s&)]+)*)*")){
            String[] splitCommands = command.split(" && ");
                commandStrings.addAll(Arrays.asList(splitCommands));
        }
        else {
            commandStrings.add(command);
        }
        return commandStrings;
    }
}