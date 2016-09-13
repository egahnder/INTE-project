package org.pucko.commands;

import java.util.ArrayList;

import org.pucko.core.OutputHandler;
import org.pucko.core.WorkingDirectory;

public class Echo extends Command {

	public Echo(ArrayList<String> args, WorkingDirectory wd, OutputHandler oh) {
		super(args, wd, oh);
	}

	@Override
	public boolean execute() {
		String outputString = "";
		for (String s : args) {
			outputString += s + " ";
		}
		outputString = outputString.trim();
		outputHandler.handleOutput(outputString);
		return true;
	}

	@Override
	public boolean validate() {

		if (args != null && args.size() != 0) {
			return true;
		}

		return false;
	}

	@Override
	public void undo() {

	}

}
