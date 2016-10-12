package org.pucko.core;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.pucko.commands.Command;

public class CommandRunnerTest {
	private ArrayList<Command> commands;
	private Command firstCommand;
	private Command secondCommand;
	private Command thirdCommand;
	private CommandRunner commandRunner;
	
	@Before
	public void setUp(){
		firstCommand = mock(Command.class);
		secondCommand = mock(Command.class);
		thirdCommand = mock(Command.class);
		commands = new ArrayList<>();
		commands.add(firstCommand);
		commands.add(secondCommand);
		commands.add(thirdCommand);
		commandRunner = new CommandRunner();
	}
	
	@Test
	public void testRunnerCallsTestOnAllValidCommands(){
		setCommandValidations(true, true, true);
		commandRunner.runCommands(commands);
		for (Command command : commands) {
			verify(command, times(1)).runCommand();
		}
	}
	

	


	private void setCommandValidations(boolean first, boolean second, boolean third){
		when(firstCommand.runCommand()).thenReturn(first);
		when(secondCommand.runCommand()).thenReturn(second);
		when(thirdCommand.runCommand()).thenReturn(third);
	}
}
