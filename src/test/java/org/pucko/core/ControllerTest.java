package org.pucko.core;

import org.junit.Before;
import org.junit.Test;
import org.pucko.commands.Command;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ControllerTest {
	private WorkingDirectory workingDirectory;
	private CommandParser commandParser;
	private CommandRunner commandRunner;
	private Controller controller;
	private OutputHandler outputHandler;

	@Before
	public void setUp(){
		outputHandler = mock(OutputHandler.class);
		workingDirectory = mock(WorkingDirectory.class);
		commandParser = mock(CommandParser.class);
		commandRunner = mock(CommandRunner.class);
		controller = new Controller(workingDirectory, commandRunner, commandParser);
	}

	@Test
	public void testControllerCallsFactoryWithCommandString(){
		String command = "command";
		controller.parseCommand(command, outputHandler);
		verify(commandParser, times(1)).parseCommands(command, workingDirectory, outputHandler);
	}
	
	@Test
	public void testControllerSendsCommandsToRunner(){
		ArrayList<Command> commandList = new ArrayList<>();
		when(commandParser.parseCommands("", workingDirectory, outputHandler)).thenReturn(commandList);
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
		assertEquals(pathString+"$ ", prompt);
	}

	@Test
	public void testPromtShortensHomeDir(){
        Path path = Paths.get(System.getProperty("user.home"));
		when(workingDirectory.getPath()).thenReturn(path);
        assertThat(controller.getPrompt(), is("~$ "));
	}
}
