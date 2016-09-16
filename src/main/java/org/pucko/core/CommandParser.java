package org.pucko.core;

import org.pucko.commands.Command;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandParser {
    private final CommandFactory commandFactory;

    public CommandParser(CommandFactory commandFactory){
        this.commandFactory = commandFactory;
    }

    public ArrayList<Command> parseCommands(String command, WorkingDirectory workingDirectory, OutputHandler outputHandler){
        ArrayList<String> commands = splitMultipleCommands(command);

        return createCommandsFromStrings(commands, workingDirectory, outputHandler);
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

    private ArrayList<Command> createCommandsFromStrings(ArrayList<String> strings, WorkingDirectory workingDirectory, OutputHandler outputHandler){
        ArrayList<Command> commands = new ArrayList<>();
        for (String commandString : strings) {
            String[] commandArray = commandString.split(" ");
            commandString = commandArray[0];
            ArrayList<String> args = new ArrayList<>(Arrays.asList(commandArray));
            Command command = commandFactory.createCommand(commandString, args, workingDirectory, outputHandler);
            commands.add(command);
        }
        return commands;

    }
}

//[^\s&]+( [^(\s&)]+)* && [^\s&]+( [^(\s&)]+)*( && [^\s&]+( [^(\s&)]+)*)*