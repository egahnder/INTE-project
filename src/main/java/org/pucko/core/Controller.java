package org.pucko.core;

import java.nio.file.Path;
import java.util.ArrayList;

import org.pucko.commands.Command;
import org.pucko.commands.CommandArguments;
import org.pucko.commands.CommandUtils;
import org.pucko.commands.CommandUtils.UtilsBuilder;

public class Controller{

	private final CommandParser commandParser;
	private final CommandRunner commandRunner;

    public Controller(CommandRunner commandrunner, CommandParser commandParser){
    	this.commandParser = commandParser;
    	this.commandRunner = commandrunner;
    }

	public void parseCommand(String command, CommandArguments commandArguments) {
		ArrayList<Command> commands = commandParser.parseCommands(command, commandArguments);
		commandRunner.runCommands(commands);
	}
}