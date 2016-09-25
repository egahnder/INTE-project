package org.pucko.core;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.pucko.commands.CommandArguments;
import org.pucko.commands.CommandUtils;

import static org.junit.Assert.*;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;


import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.Scanner;

public class MenuTest {
	private Menu menu;

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
    private ArgumentCaptor<CommandArguments> argumentsCaptor;

	@Before
	public void setUp(){
		initMocks(this);
		menu = new Menu(controller, workingDirectory);
	}
	
	@Test
	public void testControllerIsCalledWhenUserGivesInput(){
		input.provideLines("");
		menu.run();
		verify(controller, times(1)).parseCommand("", argumentsCaptor.capture());
	}
	
	@Test
	public void testSystemExit(){
		input.provideLines("exit");
		exit.expectSystemExitWithStatus(0);
		menu.run();
	}
	
	@Test
	public void testSystemOutIsCalledWithPrompt(){
		String testPrompt = "test";
		when(controller.getPrompt()).thenReturn(testPrompt);
		menu.run();
		assertEquals(testPrompt, out.getLog());
	}
	
	@Test
	public void testHandleOutput(){
		menu.handleOutput("test");
		assertEquals("test\n", out.getLog());
	}

	@Test
    public void testMenuAddsHistory(){

        String inputString = "echo Hello World";
        input.provideLines(inputString);
        menu.run();
        assertTrue(menu.getHistory().size() == 1);

    }

    @Test
    public void testMenuAddsCorrectString(){
        String inputString = "echo Hello World";
        input.provideLines(inputString);
        menu.run();
        assertEquals(inputString, menu.getHistory().get(0));
    }

}
