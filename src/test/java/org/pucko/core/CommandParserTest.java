package org.pucko.core;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.pucko.CommandProcessors.CommandProcessor;
import org.pucko.commands.Command;
import org.pucko.commands.DefaultCommand;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;


public class CommandParserTest {

    private CommandParser parser;
    private WorkingDirectory workingDirectory;
    private OutputHandler outputHandler;
    private ArrayList<Command> commandList;
    @Mock
    private Command command;
    @Mock
    private Command mockCommand;
    @Mock
    private CommandProcessor commandProcessor;
    @Mock
    private CommandFactory factory;

    @Before
    public void setUp(){
        initMocks(this);
        parser = new CommandParser(commandProcessor);
        workingDirectory = mock(WorkingDirectory.class);
        outputHandler = mock(OutputHandler.class);
        commandList = new ArrayList<>();

    }

    @Test
    public void testCommandProcessorIsCalledWithNoArgsCommand(){
        parser.parseCommands("Test", workingDirectory, outputHandler);
        verify(commandProcessor, times(1)).process(eq("Test"), eq(workingDirectory), eq(outputHandler));
    }

    @Test
    public void testCommandFactoryIsCalledWithOneArgCommand(){
        parser.parseCommands("Command arg1 arg2 arg3", workingDirectory, outputHandler);
        verify(commandProcessor, times(1)).process(eq("Command arg1 arg2 arg3"), eq(workingDirectory), eq(outputHandler));

    }

    @Test
    public void testCommandParserReturnsNotEmptyList(){
        populateCommandsList(commandList, mock(Command.class));
        when(commandProcessor.process(any(), any(), any())).thenReturn(commandList);
        ArrayList<Command> commands = parser.parseCommands("testString", workingDirectory, outputHandler);
        assertThat(commands, is(not(empty())));
    }

//    @Test
//    public void testTwoCommandsWIthNoArgs(){
//        when(P.createCommand(any(), any(), any(), any())).thenReturn(mockCommand);
//        ArrayList<Command> commands = parser.parseCommands("test1 && test2", workingDirectory, outputHandler);
//        assertThat(commands.size(), is(2));
//    }

//    @Test
//    public void testMultipleCommandIsCallsProcessorTwice(){
//        ArrayList<Command> commands = parser.parseCommands("test1 && test2", workingDirectory, outputHandler);
//        inOrder().verify(commandProcessor, times(1)).process(eq("test1"), any(), any());
//        inOrder().verify(commandProcessor, times(1)).process(eq("test2"), any(), any());
//    }

    private void populateCommandsList(ArrayList<Command> commandList, Command...commands){
        for (Command command : commands){
            commandList.add(command);
        }
    }
}
