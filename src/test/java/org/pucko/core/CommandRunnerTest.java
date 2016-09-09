package org.pucko.core;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.pucko.commands.Command;

public class CommandRunnerTest {
	ArrayList<Command> commands;
	Command firstCommand;
	Command secondCommand;
	Command thirdCommand;
	CommandRunner commandRunner;
	
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
			verify(command, times(1)).execute();
		}
	}

	@Test
	public void testUnvalidCommandIsNotRun(){
		setCommandValidations(false, true, true);
		commandRunner.runCommands(commands);
		verify(firstCommand, times(0)).execute();
	}
	
	@Test
	public void testRemainingCommandsAreNotCalledAfterInvalidCommand(){
		setCommandValidations(false, true, true);
		commandRunner.runCommands(commands);
		verify(secondCommand, times(0)).execute();
		verify(thirdCommand, times(0)).execute();
	}
	
	@Test
	public void testInvalidCommandWillCallUndoOnPreviousCommands(){
		InOrder inOrder = inOrder(firstCommand, secondCommand);
		setCommandValidations(true, true, false);
		commandRunner.runCommands(commands);
		inOrder.verify(secondCommand, times(1)).undo();
		inOrder.verify(firstCommand, times(1)).undo();
	}
	
	private void setCommandValidations(boolean first, boolean second, boolean third){
		when(firstCommand.validate()).thenReturn(first);
		when(secondCommand.validate()).thenReturn(second);
		when(thirdCommand.validate()).thenReturn(third);
	}
}
