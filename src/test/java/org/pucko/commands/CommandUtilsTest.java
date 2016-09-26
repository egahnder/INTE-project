package org.pucko.commands;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.pucko.core.InputHandler;
import org.pucko.core.OutputHandler;
import org.pucko.core.WorkingDirectory;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CommandUtilsTest{

    private CommandUtils commandUtils;
    private ArrayList<String> historyList;
    private ArrayList<String> args;

    @Mock
    private WorkingDirectory workingDirectory;
    @Mock
    private InputHandler inputHandler;
    @Mock
    private OutputHandler outputHandler;
    @Mock
    private OutputHandler errorHandler;

    @Before
    public void setUp(){
        initMocks(this);
        historyList = new ArrayList<>(Arrays.asList("test"));
        args = new ArrayList<>(Arrays.asList("testArg"));
        commandUtils = CommandUtils.builder()
                                   .addArgs(args)
                                   .addWorkingDirectory(workingDirectory)
                                   .addInputHandler(inputHandler)
                                   .addOutputHandler(outputHandler)
                                   .addErrorHandler(errorHandler)
                                   .build();
    }

    @Test
    public void testUtilsReturnsWorkingDirectoryPath(){
        Path path = commandUtils.getWorkingDirectory();
        assertThat(path, is(workingDirectory.getPath()));
    }

    @Test
    public void testUtilsChangeWorkingDirectory(){
        Path path = Paths.get("");
        commandUtils.changeWorkingDirectory(path);
        verify(workingDirectory, times(1)).changePath(path);
    }

    @Test
    public void testUtilsCallsOutputHandler(){
        commandUtils.output("test");
        verify(outputHandler, times(1)).handleOutput("test");
    }

    @Test
    public void testUtilsCallErrorHandler(){
        commandUtils.error("test");
        verify(errorHandler, times(1)).handleOutput("test");
    }

    @Test
    public void testUtilsCallInputHistory(){
        commandUtils.getHistory();
        verify(inputHandler, times(1)).getHistory();
    }

    @Test
    public void testUtilsHandlerForwardInputHandler(){
        when(inputHandler.getHistory()).thenReturn(historyList);
        assertThat(inputHandler.getHistory(), hasItems(historyList.get(0)));
    }

    @Test
    public void testUtilsReturnsArgs(){
        assertThat(commandUtils.getArgs(), hasItems(args.get(0)));
    }

    @Test
    public void testUtilsAddsArgs(){
        commandUtils.addArg("arg");
        assertThat(commandUtils.getArgs().size(), is(2));
    }
}