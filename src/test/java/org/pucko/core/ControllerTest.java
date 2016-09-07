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

	@Before
	public void setUp(){
		workingDirectory = mock(WorkingDirectory.class);
		commandFactory = mock(CommandFactory.class);
		commandRunner = mock(CommandRunner.class);
		controller = new Controller(workingDirectory, commandRunner, commandFactory);
	}

	@Test
	public void testControllerCallsFactoryWithCommandString(){
		String command = "command";
		controller.parseCommand(command);
		verify(commandFactory, times(1)).createCommands(command, workingDirectory);
	}
	
	@Test
	public void testControllerSendsCommandsToRunner(){
		ArrayList<Command> commandList = new ArrayList<>();
		when(commandFactory.createCommands(anyString(), any(WorkingDirectory.class))).thenReturn(commandList);
		controller.parseCommand("");
		verify(commandRunner, times(1)).runCommands(commandList);
	}
	
	@Test
	public void testControllerReturnsRunnerOutput(){
		String output = "";
		ArrayList<Command> commands = new ArrayList<>();
		when(commandRunner.runCommands(commands)).thenReturn(output);
		String controllerOutput = controller.parseCommand("");
		assertEquals(output, controllerOutput);
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
