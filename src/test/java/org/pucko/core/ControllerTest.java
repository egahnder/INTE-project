package org.pucko.core;

import org.junit.Before;
import org.junit.Test;
import org.pucko.commands.Command;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.nio.file.Path;
import java.util.ArrayList;

public class ControllerTest {
	private WorkingDirectory workingDirectory;
	private CommandFactory commandFactory;
	private CommandRunner commandRunner;
	private Controller controller;
	private OutputHandler outputHandler;

	@Before
	public void setUp(){
		outputHandler = mock(OutputHandler.class);
		workingDirectory = mock(WorkingDirectory.class);
		commandFactory = mock(CommandFactory.class);
		commandRunner = mock(CommandRunner.class);
		controller = new Controller(workingDirectory, commandRunner, commandFactory);
	}

	@Test
	public void testControllerCallsFactoryWithCommandString(){
		String command = "command";
		controller.parseCommand(command, outputHandler);
		verify(commandFactory, times(1)).createCommands(command, workingDirectory, outputHandler);
	}
	
	@Test
	public void testControllerSendsCommandsToRunner(){
		ArrayList<Command> commandList = new ArrayList<>();
		when(commandFactory.createCommands("", workingDirectory, outputHandler)).thenReturn(commandList);
		controller.parseCommand("", outputHandler);
		verify(commandRunner, times(1)).runCommands(commandList);
	}
	
	@Test
	public void testControllerSendsBackPrompt(){
		Path path = mock(Path.class);
		String pathString = "test";
		when(path.toString()).thenReturn(pathString);
		when(workingDirectory.getPath()).thenReturn(path);
		String prompt = controller.getPrompt();
		assertEquals(pathString, prompt);	
	}
}
