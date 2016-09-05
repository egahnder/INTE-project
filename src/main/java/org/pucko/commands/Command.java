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
	
	public abstract void addArg(String arg);
	
	public abstract String getOutput();
	
	public abstract boolean valiadate();
	
	public abstract ArrayList<String> getErrors();
	
	public abstract void pipeTo(Command command);
	
	

}

