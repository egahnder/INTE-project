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
	
	protected void setValid(boolean valid) {
	    this.valid = valid;
	}

	public abstract boolean execute();
	
	public abstract void undo();
	
	public void addArg(String arg) {
		args.add(arg);
	}
	
	public String getOutput() {
		return output;
	}
	
	public abstract boolean validate();
	
	
	public void pipeTo(Command command) {
		pipe = command;
	}
	
	

}

