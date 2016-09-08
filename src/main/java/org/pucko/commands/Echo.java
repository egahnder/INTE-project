package org.pucko.commands;

import java.util.ArrayList;

import org.pucko.core.WorkingDirectory;

public class Echo extends Command {

	public Echo(ArrayList<String> args, WorkingDirectory wd) {
		super(args, wd);
	}

	@Override
	public boolean execute() {
		output = "";
		for (String s : args) {
			output += s + " ";
		}
		output = output.trim();
		return true;
	}


	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	@Override
	public void undo() {

	}

	
}
