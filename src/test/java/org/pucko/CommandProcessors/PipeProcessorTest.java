package org.pucko.CommandProcessors;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.pucko.answers.SelfReturningAnswer;
import org.pucko.commands.Command;
import org.pucko.commands.CommandArguments;
import org.pucko.commands.CommandUtils;
import org.pucko.commands.CommandUtils.UtilsBuilder;
import org.pucko.commands.UtilsBuilderFactory;
import org.pucko.core.CommandFactory;
import org.pucko.core.InputHandler;
import org.pucko.core.OutputHandler;
import org.pucko.core.WorkingDirectory;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class PipeProcessorTest {


    private PipeProcessor processor;
    private UtilsBuilder utilsBuilder;

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
    private InputHandler inputHandler;
    @Mock
    private ArrayList<String> mockArgs;
    @Mock
    private CommandProcessor mockProcessor;
    @Mock
    private UtilsBuilderFactory utilsBuilderFactory;
    @Mock
    private CommandArguments commandArguments;
    @Mock
    private CommandUtils commandUtils;

    @Before
    public void setUp(){
        initMocks(this);
        utilsBuilder = mock(UtilsBuilder.class, new SelfReturningAnswer());
        when(utilsBuilder.build()).thenReturn(commandUtils);
        processor = new PipeProcessor(commandFactory, utilsBuilderFactory);

    }

    @Test
    public void testOnePipeReturnsTwoCommands(){
        when(utilsBuilderFactory.createUtilsBuilder()).thenReturn(utilsBuilder);
        ArrayList<Command> commands = processor.process("test1 | test2", commandArguments);
        assertThat(commands.size(), is(2));
    }

    @Test
    public void testPipedCommandCallsBuilderWithCommandAsOutputHandler(){
        UtilsBuilder secondBuilder = mock(UtilsBuilder.class, new SelfReturningAnswer());
        when(utilsBuilderFactory.createUtilsBuilder()).thenReturn(utilsBuilder).thenReturn(secondBuilder);
        when(commandFactory.createCommand("test1", commandUtils)).thenReturn(firstCommand);
        processor.process("test1 | test2", commandArguments);
        verify(secondBuilder, times(1)).addOutputHandler(firstCommand);
    }

    @Test
    public void testWrongInputCallsNext(){
        processor.setNextProcessor(mockProcessor);
        processor.process("invalidCommand", commandArguments);
        verify(mockProcessor, times(1)).process("invalidCommand", commandArguments);
    }
}
