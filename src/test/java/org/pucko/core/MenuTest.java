package org.pucko.core;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;
import org.pucko.testutilities.InputBuilder;

import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class MenuTest {
	private InputBuilder inputBuilder;
	private Controller controller;
	private Scanner scanner;
	@Rule
	public ExpectedSystemExit exit = ExpectedSystemExit.none();
	
	@Rule
	public SystemOutRule out = new SystemOutRule().enableLog();
	
	@Rule
	public final TextFromStandardInputStream input = emptyStandardInputStream();
	
	@Before
	public void setUp(){
		inputBuilder = new InputBuilder();
		controller = mock(Controller.class);
		scanner = new Scanner(System.in);
	}
	
	@Test
	public void testControllerIsCalledWhenUserGivesInput(){	
		String inputString = "";		
		input.provideLines(inputString);
		Menu menu = new Menu(controller, scanner);
		menu.run();
		verify(controller, times(1)).parseCommand(inputString);
	}
	
	@Test
	public void testSystemExit(){

		input.provideLines("exit");
		Menu menu = new Menu(controller, scanner);
		exit.expectSystemExitWithStatus(0);
		menu.run();
	}
	
	@Test
	public void testSystemOutIsCalledWithPromt(){
		
	}
}
