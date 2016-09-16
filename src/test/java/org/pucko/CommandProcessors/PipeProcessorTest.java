package org.pucko.CommandProcessors;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.pucko.commands.Command;
import org.pucko.core.CommandFactory;
import org.pucko.core.OutputHandler;
import org.pucko.core.WorkingDirectory;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class PipeProcessorTest {


    private PipeProcessor processor;

    @Mock
    private CommandFactory commandFactory;
    @Mock
    private Command firstCommand;
    @Mock
    private Command secondCommand;
    @Mock
    private WorkingDirectory workingDirectory;
    @Mock
    private OutputHandler outputHandler;
    @Mock
    private ArrayList<String> mockArgs;
    @Mock
    private CommandProcessor mockProcessor;

    @Before
    public void setUp(){
        initMocks(this);
        processor = new PipeProcessor(commandFactory);

    }

    @Test
    public void testOnePipeReturnsTwoCommands(){
        ArrayList<Command> commands = processor.process("test1 | test2", workingDirectory, outputHandler);
        assertThat(commands.size(), is(2));
    }

    @Test
    public void testPipedCommandCallsFactoryWithCommandAsOutputHandler(){
        when(commandFactory.createCommand(eq("test1"), any(), eq(workingDirectory), eq(outputHandler))).thenReturn(firstCommand);
        processor.process("test1 | test2", workingDirectory, outputHandler);
        verify(commandFactory, times(1)).createCommand(eq("test2"), any(), eq(workingDirectory), eq(firstCommand));
    }

    @Test
    public void testWrongInputCallsNext(){
        processor.setNextProcessor(mockProcessor);
        processor.process("invalidCommand", workingDirectory, outputHandler);
        verify(mockProcessor, times(1)).process("invalidCommand", workingDirectory, outputHandler);
    }
}
