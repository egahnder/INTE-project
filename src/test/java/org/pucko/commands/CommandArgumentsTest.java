package org.pucko.commands;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.pucko.commands.CommandUtils.UtilsBuilder;
import org.pucko.core.InputHandler;
import org.pucko.core.OutputHandler;
import org.pucko.core.WorkingDirectory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by eric on 2016-09-24.
 */
public class CommandArgumentsTest {

    @Mock
    private UtilsBuilder utilsBuilder;
    @Mock
    private WorkingDirectory workingDirectory;
    @Mock
    private OutputHandler outputHandler;
    @Mock
    private OutputHandler errorHandler;
    @Mock
    private InputHandler inputHandler;


    @Before
    public void setUp(){
        initMocks(this);
    }

    @Test
    public void testTransferDataReturnsSameUtilsBuilder(){
        CommandArguments commandArguments = CommandArguments.builder().build();
        assertThat(utilsBuilder, is(commandArguments.transferData(utilsBuilder)));
    }

    @Test
    public void testArgumentsTransferWorkingDirectory(){
        CommandArguments commandArguments = CommandArguments.builder()
                                                            .addWorkingDirectory(workingDirectory)
                                                            .build();
        commandArguments.transferData(utilsBuilder);
        verify(utilsBuilder, times(1)).addWorkingDirectory(workingDirectory);
    }

    @Test
    public void testArgumentsTransferErrorHandler(){
        CommandArguments commandArguments = CommandArguments.builder()
                                                            .addErrorHandler(errorHandler)
                                                            .build();
        commandArguments.transferData(utilsBuilder);
        verify(utilsBuilder, times(1)).addErrorHandler(errorHandler);
    }

    @Test
    public void testArgumentsTransferOutputHandler(){
        CommandArguments commandArguments = CommandArguments.builder()
                                                            .addOutputHandler(outputHandler)
                                                            .build();
        commandArguments.transferData(utilsBuilder);
        verify(utilsBuilder, times(1)).addOutputHandler(outputHandler);
    }
    @Test
    public void testArgumentsTransferInputHandler(){
        CommandArguments commandArguments = CommandArguments.builder()
                                                            .addInputHandler(inputHandler)
                                                            .build();
        commandArguments.transferData(utilsBuilder);
        verify(utilsBuilder, times(1)).addInputHandler(inputHandler);
    }
}

