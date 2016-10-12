package org.pucko.core;

import java.util.ArrayList;

import org.pucko.commands.Command;
import org.pucko.commands.CommandArguments;

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