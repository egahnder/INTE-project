package org.pucko.core;

import java.nio.file.Path;
import java.util.ArrayList;

import org.pucko.commands.Command;

public class Controller{

	private final CommandParser commandParser;
	private final WorkingDirectory workingDirectory;
	private final CommandRunner commandRunner;

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
		String pathString = path.toString();
		String homeString = System.getProperty("user.home");
		if (pathString.startsWith(homeString)){
			pathString = pathString.replaceFirst(homeString, "~");
		}
		return pathString+"$ ";
	}
	
}