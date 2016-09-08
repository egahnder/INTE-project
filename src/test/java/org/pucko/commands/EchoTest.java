package org.pucko.commands;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.pucko.core.WorkingDirectory;

import static org.mockito.Mockito.*;

import java.util.ArrayList;


public class EchoTest {

	private WorkingDirectory wd;
	private ArrayList<String> validArgs;
	private ArrayList<String> invalidArgs;
	
	
	
	@Before
	public void setUp(){
		wd = mock(WorkingDirectory.class);
		validArgs = new ArrayList<>();
		validArgs.add("Hello World");
		invalidArgs = new ArrayList<>();
		invalidArgs.add(null);
		invalidArgs.add("");
	}
	
	@Test
	public void testExecuteSetsOutput(){
		Echo e = new Echo(validArgs, wd);
		e.execute();
		assertEquals(validArgs.get(0), e.getOutput());
	}
	
	@Test
	public void testExecute(){
		
		Echo e = new Echo(validArgs, wd);
		assertTrue(e.execute());
		
	}
	
	
	
	
	
}
