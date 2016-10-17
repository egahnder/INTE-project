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
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class DefaultProcessorTest {


    private DefaultProcessor processor;

    private UtilsBuilder utilsBuilder;

    @Mock
    private CommandFactory factory;
    @Mock
    private CommandArguments commandArguments;
    @Mock
    private UtilsBuilderFactory utilsBuilderFactory;
    @Mock
    private CommandUtils commandUtils;
    @Mock
    private Command command;

    @Before
    public void setUp(){
        initMocks(this);
        processor = new DefaultProcessor(factory, utilsBuilderFactory);
        utilsBuilder = mock(UtilsBuilder.class, new SelfReturningAnswer());
    }

    @Test
    public void testProcessorReturnsCommands(){
        when(utilsBuilderFactory.createUtilsBuilder()).thenReturn(utilsBuilder);
        when(utilsBuilder.build()).thenReturn(commandUtils);
        when(commandArguments.getErrorHandler()).thenReturn(mock(OutputHandler.class));
        when(commandArguments.getInputHandler()).thenReturn(mock(InputHandler.class));
        when(commandArguments.getWorkingDirectory()).thenReturn(mock(WorkingDirectory.class));
        when(commandArguments.getOutputHandler()).thenReturn(mock(OutputHandler.class));
        when(factory.createCommand("Test", commandUtils)).thenReturn(command);
        ArrayList<Command> commandList = processor.process("Test", commandArguments);
        assertThat(commandList, hasItem(command));
    }
}