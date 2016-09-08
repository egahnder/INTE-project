package org.pucko.commands;

import java.util.ArrayList;

import org.pucko.core.OutputHandler;
import org.pucko.core.WorkingDirectory;

public abstract class Command implements OutputHandler{

	protected String name;
	//TODO Remove when OutputHandler is fully implemented
	protected String output;
	//TODO Remove when OutputHandler is fully implemented
	protected Command pipe;
	private OutputHandler outputHandler;
	protected ArrayList<String> args;
	protected WorkingDirectory workingDirectory;
	protected boolean valid;
	
	
	//TODO Remove when OutputHandler is fully implemented
	public Command(ArrayList<String> commands, WorkingDirectory workingDirectory) {
	    this.args = commands;
	    this.workingDirectory = workingDirectory;
	}
	
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
	
	
	//TODO make private when OutputHandler is fully implemented
	public void addArg(String arg) {
		args.add(arg);
	}
	
	//TODO Remove when OutputHandler is fully implemented. Use output(String) instead
	public String getOutput() {
		return output;
	}
	
	public abstract boolean validate();

	@Override
	public void handleOutput(String output){
		// TODO Auto-generated method stub		
	}
	
	protected void output(String output){
		outputHandler.handleOutput(output);
	}
}

