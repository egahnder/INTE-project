package org.pucko.core;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.pucko.commands.Command;
import org.pucko.commands.CommandArguments;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class ControllerTest {
    private Controller controller;
    @Mock
    private WorkingDirectory workingDirectory;
    @Mock
    private CommandParser commandParser;
    @Mock
    private CommandRunner commandRunner;
    @Mock
	private OutputHandler outputHandler;
    @Mock
	private InputHandler inputHandler;
    @Mock
    private CommandArguments commandArguments;


	@Before
	public void setUp(){
        initMocks(this);
		controller = new Controller(commandRunner, commandParser);
	}

	@Test
	public void testControllerCallsFactoryWithCommandString(){
		String command = "command";
		controller.parseCommand(command, commandArguments);
		verify(commandParser, times(1)).parseCommands(command, commandArguments);
	}
	
	@Test
	public void testControllerSendsCommandsToRunner(){
		ArrayList<Command> commandList = new ArrayList<>();
		when(commandParser.parseCommands(any(String.class), any(CommandArguments.class) )).thenReturn(commandList);
		controller.parseCommand("", commandArguments);
		verify(commandRunner, times(1)).runCommands(commandList);
	}
}
