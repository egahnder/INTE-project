package org.pucko.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.pucko.commands.CdCommandMethod;
import org.pucko.commands.Command;
import org.pucko.commands.EchoCommandMethod;

public class CommandFactory {
    ArrayList<Command> createdCommand = new ArrayList<>();
    ArrayList<String> args = new ArrayList<>();
    HashMap<String, CommandMethod> validCommands = new HashMap<>();
    String[] allArgs;
    WorkingDirectory wd;
    
    
    public CommandFactory() {
        validCommands.put("cd", new CdCommandMethod());
        validCommands.put("echo", new EchoCommandMethod());
    }
    
	public ArrayList<Command> createCommands(String commandString, WorkingDirectory workingDirectory, OutputHandler outputHandler){
	    this.wd = workingDirectory;
	    allArgs = commandString.split("[\\s\\xA0]+");
	    
	    if (!validCommands.containsKey(allArgs[0])) {
	        System.out.println("Invalid Command");
	        return null;
	    }
	   String commandToCreate = allArgs[0];
	   
	   // Remove the command from allArgs array
	   allArgs = Arrays.copyOfRange(allArgs, 1, allArgs.length);
	   createdCommand.add(validCommands.get(commandToCreate).runMethod(allArgs, wd));
	    
		return createdCommand;		
	}

}
