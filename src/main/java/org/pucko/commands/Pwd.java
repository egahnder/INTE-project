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

		if (validate()) {
			output(workingDirectory.getPath().toString());
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean validate() {

		if (workingDirectory == null) {
			return false;
		}
		return true;
	}

	@Override
	public void undo() {

	}
}
