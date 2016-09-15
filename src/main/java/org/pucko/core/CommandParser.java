package org.pucko.core;

import org.pucko.commands.Command;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandParser {
    private CommandFactory commandFactory;

    public CommandParser(CommandFactory commandFactory){
        this.commandFactory = commandFactory;
    }

    public ArrayList<Command> parseCommands(String commands, WorkingDirectory workingDirectory, OutputHandler outputHandler){
        ArrayList<Command> commandList = new ArrayList<>();
        String[] commandArray = commands.split("\\s+");
        String commandString = commandArray[0];
        if (commandArray.length > 1) {
            commandArray = Arrays.copyOfRange(commandArray, 1, commandArray.length);
        }
        else {
            commandArray = new String[0];
        }
        ArrayList<String> args = new ArrayList<>(Arrays.asList(commandArray));
        Command command = commandFactory.createCommand(commandString, args, workingDirectory, outputHandler);
        commandList.add(command);
        return commandList;
    }
}
