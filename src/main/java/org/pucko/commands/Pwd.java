package org.pucko.commands;

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


}
