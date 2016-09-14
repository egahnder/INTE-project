package org.pucko.core;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.pucko.commands.Command;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;


public class CommandParserTest {

    CommandParser parser;
    CommandFactory factory;
    WorkingDirectory workingDirectory;
    OutputHandler outputHandler;

    @Captor
    private ArgumentCaptor<ArrayList<String>> argsCaptor;

    @Mock
    private ArrayList<String> mockedArray;

    @Before
    public void setUp(){
        initMocks(this);
        factory = mock(CommandFactory.class);
        parser = new CommandParser(factory);
        workingDirectory = mock(WorkingDirectory.class);
        outputHandler = mock(OutputHandler.class);
    }

    @Test
    public void testParserReturnsCommandList(){
        ArrayList<Command> commands = parser.parseCommands("testString", workingDirectory, outputHandler);
        assertThat(commands, is(notNullValue()));
    }

    @Test
    public void testCommandFactoryIsCalledWithNoArgsCommand(){
        parser.parseCommands("Test", workingDirectory, outputHandler);
        verify(factory, times(1)).createCommand(eq("Test"), argsCaptor.capture(), eq(workingDirectory), eq(outputHandler));
        ArrayList<String> argsList = argsCaptor.getValue();
        assertThat(argsList, is(empty()));
    }

    @Test
    public void testCommadFactoryIsCalledWithOneArgCommand(){
        parser.parseCommands("Command arg1 arg2 arg3", workingDirectory, outputHandler);
        verify(factory, times(1)).createCommand(eq("Command"), argsCaptor.capture(), eq(workingDirectory), eq(outputHandler));
        ArrayList<String> argsList = argsCaptor.getValue();
        assertThat(argsList, contains("arg1", "arg2", "arg3"));
    }

    @Test
    public void testCommandParserReturnsNotEmptyList(){
        ArrayList<Command> commands = parser.parseCommands("testString", workingDirectory, outputHandler);
        assertThat(commands, is(not(empty())));
    }

}
