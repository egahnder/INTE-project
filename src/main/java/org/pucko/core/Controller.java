package org.pucko.core;

import java.nio.file.Path;
import java.util.ArrayList;

import org.pucko.commands.Command;

public class Controller{

	private CommandParser commandParser;
	private WorkingDirectory workingDirectory;
	private CommandRunner commandRunner;

    public Controller(WorkingDirectory workingdirectory, CommandRunner commandrunner, CommandParser commandParser){
    	this.commandParser = commandParser;
    	this.workingDirectory = workingdirectory;
    	this.commandRunner = commandrunner;
    }

	public void parseCommand(String input, OutputHandler outputHandler) {
		ArrayList<Command> commands = commandParser.parseCommands(input, workingDirectory, outputHandler);
		commandRunner.runCommands(commands);
	}
	
	public String getPrompt(){
		Path path = workingDirectory.getPath();
		return path.toString()+" $ ";
	}
	
}