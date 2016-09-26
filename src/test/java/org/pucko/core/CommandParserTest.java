package org.pucko.core;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.pucko.CommandProcessors.CommandProcessor;
import org.pucko.commands.Command;
import org.pucko.commands.CommandArguments;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;


public class CommandParserTest {

    private CommandParser parser;
    private ArrayList<Command> commandList;
    @Mock
    private Command command;
    @Mock
    private Command mockCommand;
    @Mock
    private CommandProcessor commandProcessor;
    @Mock
    private CommandFactory factory;
    @Mock
    private CommandArguments commandArguments;

    @Before
    public void setUp(){
        initMocks(this);
        parser = new CommandParser(commandProcessor);
        commandList = new ArrayList<>();

    }

    @Test
    public void testCommandProcessorIsCalledWithNoArgsCommand(){
        parser.parseCommands("Test", commandArguments);
        verify(commandProcessor, times(1)).process("Test", commandArguments);
    }

    @Test
    public void testCommandFactoryIsCalledWithMultipleArgCommand(){
        parser.parseCommands("Command arg1 arg2 arg3", commandArguments);
        verify(commandProcessor, times(1)).process("Command arg1 arg2 arg3", commandArguments);

    }

    @Test
    public void testCommandParserReturnsNotEmptyList(){
        populateCommandsList(commandList, mock(Command.class));
        when(commandProcessor.process(any(String.class), any(CommandArguments.class))).thenReturn(commandList);
        ArrayList<Command> commands = parser.parseCommands("testString", commandArguments);
        assertThat(commands, is(not(empty())));
    }

    private void populateCommandsList(ArrayList<Command> commandList, Command...commands){
        for (Command command : commands){
            commandList.add(command);
        }
    }
}
