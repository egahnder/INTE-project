package org.pucko.commands;

import java.util.ArrayList;

import org.pucko.core.WorkingDirectory;

public abstract class Command {

	private String name;
	private String output;
	private Command pipe;
	private ArrayList<String> args;
	private ArrayList<String> validationErrors;
	private WorkingDirectory workingDirectory;
	boolean valid;

	public Command(ArrayList<String> commands, WorkingDirectory workingDirectory) {

	}

	public Command(ArrayList<String> commands, WorkingDirectory workingDirectory, Command command) {

	}

	public abstract boolean execute();
	
	public abstract void undo();
	
	public void addArg(String arg) {
		
	}
	
	public String getOutput() {
		return null;
	}
	
	public abstract boolean valiadate();
	
	public ArrayList<String> getErrors() {
		return new ArrayList<String>();
	}
	
	public void pipeTo(Command command) {
		
	}
	
	

}

