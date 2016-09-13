package org.pucko.commands;

import java.util.ArrayList;

import org.pucko.core.OutputHandler;
import org.pucko.core.WorkingDirectory;

public abstract class Command implements OutputHandler{

	protected String name;
	protected OutputHandler outputHandler;
	protected ArrayList<String> args;
	protected WorkingDirectory workingDirectory;
	protected boolean valid;
	
	public Command(ArrayList<String> commands, WorkingDirectory workingDirectory, OutputHandler outputHandler) {
	    this.args = commands;
	    this.workingDirectory = workingDirectory;
	    this.outputHandler = outputHandler;
	}
	
	
	protected void setValid(boolean valid) {
	    this.valid = valid;
	}

	public abstract boolean execute();
	
	public abstract void undo();

	public abstract boolean validate();

	@Override
	public void handleOutput(String output){
		args.add(output);
	}
	
	protected void output(String output){
		outputHandler.handleOutput(output);
	}
}

