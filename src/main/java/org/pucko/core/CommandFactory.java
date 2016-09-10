package org.pucko.core;

import java.util.ArrayList;

import org.pucko.commands.Cd;
import org.pucko.commands.Command;


public class CommandFactory {
    ArrayList<Command> createdCommand = new ArrayList<>();
    ArrayList<String> args = new ArrayList<>();
    
	public ArrayList<Command> createCommands(String commandString, WorkingDirectory workingDirectory, OutputHandler outputHandler){
	    	   
	    args.add("..");
	    
	    Cd cd = new Cd(args, workingDirectory);
	    
	    createdCommand.add(cd);
	    
		return createdCommand;		
	}

}
