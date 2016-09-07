package org.pucko.commands;

import java.util.ArrayList;

import org.pucko.core.WorkingDirectory;

public abstract class Command {

	protected String name;
	protected String output;
	protected Command pipe;
	protected ArrayList<String> args;
	protected WorkingDirectory workingDirectory;
	protected boolean valid;

	public Command(ArrayList<String> commands, WorkingDirectory workingDirectory) {
	    this.args = commands;
	    this.workingDirectory = workingDirectory;

	}

	public Command(ArrayList<String> commands, WorkingDirectory workingDirectory, Command command) {
	    this.args = commands;
        this.workingDirectory = workingDirectory;
        this.pipe = command;

	}

	public abstract boolean execute();
	
	public abstract void undo();
	
	public void addArg(String arg) {
		
	}
	
	public String getOutput() {
		return null;
	}
	
	public abstract boolean validate();
	
	
	public void pipeTo(Command command) {
		
	}
	
	

}

