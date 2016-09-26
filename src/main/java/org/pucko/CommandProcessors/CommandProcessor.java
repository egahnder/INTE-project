package org.pucko.CommandProcessors;

import org.pucko.commands.Command;
import org.pucko.commands.CommandArguments;
import org.pucko.commands.CommandUtils;
import org.pucko.commands.CommandUtils.UtilsBuilder;
import org.pucko.commands.UtilsBuilderFactory;
import org.pucko.core.CommandFactory;
import org.pucko.core.InputHandler;
import org.pucko.core.OutputHandler;
import org.pucko.core.WorkingDirectory;

import java.util.ArrayList;
import java.util.Arrays;


public abstract class CommandProcessor {

    private CommandProcessor nextProcessor;
    protected final CommandFactory commandFactory;
    protected final UtilsBuilderFactory utilsBuilderFactory;

    public CommandProcessor(CommandFactory commandFactory, UtilsBuilderFactory utilsBuilderFactory){
        this.commandFactory = commandFactory;
        this.utilsBuilderFactory = utilsBuilderFactory;
    }

    public abstract ArrayList<Command> process(String command, CommandArguments commandArguments);

    protected ArrayList<Command> sendToNextProcessor(String command, CommandArguments commandArguments){
        if (nextProcessor != null){
            return nextProcessor.process(command, commandArguments);
        }
        else{
            return new ArrayList<>();
        }
    }

    protected ArrayList<String> splitCommand(String command){
        String[] commandArray = command.split(" ");
        return new ArrayList<>(Arrays.asList(commandArray));
    }

    protected UtilsBuilder getUtilsBuilder(CommandArguments commandArguments, ArrayList<String> args) {
        UtilsBuilder builder = utilsBuilderFactory.createUtilsBuilder();
        return builder
                .addArgs(args)
                .addErrorHandler(commandArguments.getErrorHandler())
                .addOutputHandler(commandArguments.getOutputHandler())
                .addWorkingDirectory(commandArguments.getWorkingDirectory())
                .addInputHandler(commandArguments.getInputHandler());
    }

    public void setNextProcessor(CommandProcessor nextProcessor){
        this.nextProcessor = nextProcessor;
    }
}
