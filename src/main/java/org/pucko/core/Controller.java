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

	public String parseCommand(String input) {
		ArrayList<Command> commands = commandFactory.createCommands(input, workingDirectory);
		commandRunner.runCommands(commands);
		return null;
		
	}
	
	public String getPrompt(){
		Path path = workingDirectory.getPath();
		return path.toString();
	}
	
}