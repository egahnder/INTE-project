package org.pucko.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.pucko.commands.Command;

public class CommandRunner {
	StringBuilder stringBuilder;
	public CommandRunner() {
		stringBuilder = new StringBuilder();
	}
	
	public String runCommands(ArrayList<Command> commands){
		for (Command command : commands) {
			if (command.valiadate()) {
				command.execute();
				extractOutput(command);
			}
			else{
				extractOutput(command);
				undoCommands(commands, command);
				break;
			}
		}		
		return stringBuilder.toString();
	}

	private void extractOutput(Command command) {
		String output = command.getOutput();
		if (output != null && !output.equals("")) {
			stringBuilder.append(output);			
			stringBuilder.append(System.lineSeparator());
		}
	}

	private void undoCommands(ArrayList<Command> commands, Command command) {
		int commandIndex = commands.indexOf(command);
		List<Command> undoList = commands.subList(0, commandIndex);
		Collections.reverse(undoList);
		for (Command com : undoList) {
			extractOutput(com);
			com.undo();
		}
	}

}
