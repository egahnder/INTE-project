package org.pucko.core;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mock;
import org.pucko.answers.SelfReturningAnswer;
import org.pucko.commands.CommandArguments;
import org.pucko.commands.CommandArguments.ArgumentsBuilder;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class MenuTest {
	private Menu menu;
    private ArgumentsBuilder argumentsBuilder;

    @Mock
    private ArgumentsBuilderFactory argumentsBuilderFactory;
    @Mock
    private CommandArguments commandArguments;
    @Mock
    private Controller controller;
    @Mock
    private WorkingDirectory workingDirectory;

	@Rule
	public ExpectedSystemExit exit = ExpectedSystemExit.none();
	@Rule
	public SystemOutRule out = new SystemOutRule().enableLog();
	@Rule
	public final TextFromStandardInputStream input = emptyStandardInputStream();
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

	@Before
	public void setUp(){
		initMocks(this);
        argumentsBuilder = mock(ArgumentsBuilder.class, new SelfReturningAnswer());
        when(argumentsBuilderFactory.createBuilder()).thenReturn(argumentsBuilder);
		menu = new Menu(controller, workingDirectory, argumentsBuilderFactory);
	}
	
	@Test
	public void testArgumentsBuilderAddsWorkingDirectory(){
        when(workingDirectory.getPath()).thenReturn(temporaryFolder.getRoot().toPath());
        input.provideLines("");
        menu.run();
        verify(argumentsBuilder, times(1)).addWorkingDirectory(workingDirectory);
	}

	@Test
    public void testArgumentsBuilderAddsInputHandler(){
        when(workingDirectory.getPath()).thenReturn(temporaryFolder.getRoot().toPath());
        input.provideLines("");
        menu.run();
        verify(argumentsBuilder, times(1)).addInputHandler(menu);
    }

    @Test
    public void testArgumentsBuilderAddsErrorHandler(){
        when(workingDirectory.getPath()).thenReturn(temporaryFolder.getRoot().toPath());
        input.provideLines("");
        menu.run();
        verify(argumentsBuilder, times(1)).addErrorHandler(menu);
    }

    @Test
    public void testArgumentsBuilderAddsOutputHandler(){
        when(workingDirectory.getPath()).thenReturn(temporaryFolder.getRoot().toPath());
        input.provideLines("");
        menu.run();
        verify(argumentsBuilder, times(1)).addOutputHandler(menu);
    }

    @Test
    public void testControllerIsCalledWithCommandArguments(){
        when(workingDirectory.getPath()).thenReturn(temporaryFolder.getRoot().toPath());
        when(argumentsBuilder.build()).thenReturn(commandArguments);
        input.provideLines("");
        menu.run();
        verify(controller, times(1)).parseCommand("", commandArguments);
    }
	
	@Test
	public void testSystemExit(){
        when(workingDirectory.getPath()).thenReturn(temporaryFolder.getRoot().toPath());
		input.provideLines("exit");
		exit.expectSystemExitWithStatus(0);
		menu.run();
	}
	
	@Test
	public void testSystemOutIsCalledWithPrompt(){
        when(workingDirectory.getPath()).thenReturn(temporaryFolder.getRoot().toPath());
        String expectedPrompt = temporaryFolder.getRoot().toPath().toString()+"$ ";
		menu.run();
		assertEquals(expectedPrompt, out.getLog());
	}
	
	@Test
	public void testHandleOutput(){
		menu.handleOutput("test");
		assertEquals("test\n", out.getLog());
	}

	@Test
    public void testMenuAddsHistory(){
        when(workingDirectory.getPath()).thenReturn(temporaryFolder.getRoot().toPath());
        String inputString = "echo Hello World";
        input.provideLines(inputString);
        menu.run();
        assertTrue(menu.getHistory().size() == 1);

    }

    @Test
    public void testMenuAddsCorrectString(){
        when(workingDirectory.getPath()).thenReturn(temporaryFolder.getRoot().toPath());
        String inputString = "echo Hello World";
        input.provideLines(inputString);
        menu.run();
        assertEquals(inputString, menu.getHistory().get(0));
    }

    @Test
    public void testMenuReplacesHomeWithTilde(){
        Path path = Paths.get(System.getProperty("user.home"));
        when(workingDirectory.getPath()).thenReturn(path);
        menu.run();
        String output = out.getLog();
        assertEquals("~$ ", output);
    }

}
