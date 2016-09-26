package org.pucko.commands;

import java.util.ArrayList;

import org.pucko.core.InputHandler;
import org.pucko.core.OutputHandler;
import org.pucko.core.WorkingDirectory;

public class Pwd extends Command {


	/**
	 * @param commandUtils Utils used for IO operations, command arguments, working directory etc.
	 */
	public Pwd(CommandUtils commandUtils) {
		super(commandUtils);
	}

	@Override
	public boolean execute() {
			output(getWorkingDirectory().toString());
			return true;
	}

	@Override
	protected boolean verifyExecutable() {
		return getWorkingDirectory() != null;
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
