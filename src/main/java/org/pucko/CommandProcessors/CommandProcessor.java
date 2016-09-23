package org.pucko.CommandProcessors;

import org.pucko.commands.Command;
import org.pucko.core.CommandFactory;
import org.pucko.core.InputHandler;
import org.pucko.core.OutputHandler;
import org.pucko.core.WorkingDirectory;

import java.util.ArrayList;
import java.util.Arrays;


public abstract class CommandProcessor {

    private CommandProcessor nextProcessor;
    protected final CommandFactory commandFactory;

    public CommandProcessor(CommandFactory commandFactory){
        this.commandFactory = commandFactory;
    }

    public abstract ArrayList<Command> process(String command, WorkingDirectory workingDirectory, OutputHandler outputHandler, InputHandler inputHandler);

    protected ArrayList<Command> sendToNextProcessor(String command, WorkingDirectory workingDirectory, OutputHandler outputHandler, InputHandler inputHandler){
        if (nextProcessor != null){
            return nextProcessor.process(command, workingDirectory, outputHandler, inputHandler);
        }
        else{
            return new ArrayList<>();
        }
    }

    protected Command createCommandFromString(String commandString, WorkingDirectory workingDirectory, OutputHandler outputHandler, InputHandler inputHandler){
        return createCommandFromString(commandString, workingDirectory, outputHandler, outputHandler, inputHandler);
    }

    protected Command createCommandFromString(String commandString, WorkingDirectory workingDirectory, OutputHandler outputHandler, OutputHandler errorHandler, InputHandler inputHandler) {
        String[] commandArray = commandString.split(" ");
        commandString = commandArray[0];
        ArrayList<String> args = new ArrayList<>(Arrays.asList(commandArray));
        return commandFactory.createCommand(commandString, args, workingDirectory, outputHandler, errorHandler, inputHandler);
    }

    public void setNextProcessor(CommandProcessor nextProcessor){
        this.nextProcessor = nextProcessor;
    }
}
