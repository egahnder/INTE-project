package org.pucko.core;

import java.nio.file.Path;
import java.util.ArrayList;

import org.pucko.commands.Command;

public class Controller{
	
	CommandFactory commandFactory;
	WorkingDirectory workingDirectory;
	CommandRunner commandRunner;

    public Controller(WorkingDirectory workingdirectory, CommandRunner commandrunner, CommandFactory commandfactory){
    	this.commandFactory = commandfactory;
    	this.workingDirectory = workingdirectory;
    	this.commandRunner = commandrunner;
    }

	public void parseCommand(String input, OutputHandler outputHandler) {
		ArrayList<Command> commands = commandFactory.createCommands(input, workingDirectory, outputHandler);
		commandRunner.runCommands(commands);
		
	}
	
	public String getPrompt(){
		Path path = workingDirectory.getPath();
		return path.toString();
	}
	
}