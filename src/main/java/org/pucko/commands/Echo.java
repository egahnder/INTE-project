package org.pucko.commands;

import java.util.ArrayList;

import org.pucko.core.WorkingDirectory;

public class Echo extends Command {

	public Echo(ArrayList<String> args, WorkingDirectory wd) {
		super(args, wd);
	}

	@Override
	public boolean execute() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}

}
