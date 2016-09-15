package org.pucko.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.pucko.commands.Command;

public class CommandRunner {
	
	public void runCommands(ArrayList<Command> commands){
		for (Command command : commands) {
			if (!command.runCommand()) {
				undoCommands(commands, command);
				break;
			}
		}
	}

	private void undoCommands(ArrayList<Command> commands, Command command) {
		int commandIndex = commands.indexOf(command);
		List<Command> undoList = commands.subList(0, commandIndex);
		Collections.reverse(undoList);
		undoList.forEach(Command::revertCommand);
	}

}
