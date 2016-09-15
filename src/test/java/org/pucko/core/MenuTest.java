package org.pucko.core;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import static org.junit.Assert.*;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;


import static org.mockito.Mockito.*;

import java.util.Scanner;

public class MenuTest {
	private Controller controller;
	private Scanner scanner;
	private Menu menu;
	@Rule
	public ExpectedSystemExit exit = ExpectedSystemExit.none();
	
	@Rule
	public SystemOutRule out = new SystemOutRule().enableLog();
	
	@Rule
	public final TextFromStandardInputStream input = emptyStandardInputStream();
	
	@Before
	public void setUp(){
		controller = mock(Controller.class);
		scanner = new Scanner(System.in);
		menu = new Menu(controller, scanner);
	}
	
	@Test
	public void testControllerIsCalledWhenUserGivesInput(){	
		String inputString = "";		
		input.provideLines(inputString);
		menu.run();
		verify(controller, times(1)).parseCommand(inputString, menu);
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


}
