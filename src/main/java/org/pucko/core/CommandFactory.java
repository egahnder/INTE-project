package org.pucko.core;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.pucko.commands.*;

public class CommandFactory {
    private HashMap<String, CommandMethod> validCommands = new HashMap<>();

    public CommandFactory() {
        validCommands.put("cd", new CdCommandMethod());
        validCommands.put("echo", new EchoCommandMethod());
    }
    
	public ArrayList<Command> createCommands(String commandString, WorkingDirectory workingDirectory, OutputHandler outputHandler){
		String[] allArgs = commandString.split("[\\s\\xA0]+");
		ArrayList<Command> createdCommand = new ArrayList<>();

	    if (!validCommands.containsKey(allArgs[0])) {
	        ArrayList<String> invalidCommand = new ArrayList<>();
            invalidCommand.add("Invalid Command");
            createdCommand.add(new DefaultCommand(invalidCommand, workingDirectory, outputHandler));
            return createdCommand;

	    }
	    
	   String commandToCreate = allArgs[0];

	   // Remove the command from allArgs array
	   allArgs = Arrays.copyOfRange(allArgs, 1, allArgs.length);
	   createdCommand.add(validCommands.get(commandToCreate).runMethod(allArgs, workingDirectory, outputHandler));

		return createdCommand;		
	}

}
