package org.pucko.commands;

import java.util.ArrayList;

import org.pucko.core.OutputHandler;
import org.pucko.core.WorkingDirectory;

public class Pwd extends Command {

	public Pwd(ArrayList<String> args, WorkingDirectory wd, OutputHandler oh) {
		super(args, wd, oh);
	}

	@Override
	public boolean execute() {
			output(getWorkingDirectory().toString());
			return true;
	}

	@Override
	protected boolean verifyExecutable() {
		if (getWorkingDirectory() == null) {
			return false;
		}
		return true;
	}

	@Override
	protected boolean undo() {
		return false;
	}


	@Override
	protected boolean verifyUndoable() {
		return false;
	}
}
