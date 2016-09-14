package org.pucko.commands;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.pucko.core.OutputHandler;
import org.pucko.core.WorkingDirectory;

import static org.mockito.Mockito.*;

import java.util.ArrayList;

public class EchoTest {

	private WorkingDirectory wd;
	private OutputHandler oh;

	@Before
	public void setUp() {
		wd = mock(WorkingDirectory.class);
		oh = mock(OutputHandler.class);
	}

	@Test
	public void testExecuteSetsSingleWordOutput() {
		ArrayList<String> inputArgs = new ArrayList<>();
		inputArgs.add("Hello");

		Echo e = new Echo(inputArgs, wd, oh);
		e.execute();
		verify(oh, times(1)).handleOutput("Hello");

	}

	@Test
	public void testExecuteSetsMultipleWordOutput() {
		String[] input = { "Hello", "World!" };
		ArrayList<String> inputArgs = populateArrayList(input);

		Echo e = new Echo(inputArgs, wd, oh);
		e.execute();
		verify(oh, times(1)).handleOutput("Hello World!");

	}

	@Test
	public void testValidateWithNullInput() {

		ArrayList<String> input = null;
		Echo e = new Echo(input, wd, oh);
		assertFalse(e.runCommand());

	}

	@Test
	public void testValidateWithZeroInput() {

		ArrayList<String> input = new ArrayList<>();
		Echo e = new Echo(input, wd, oh);
		assertFalse(e.runCommand());

	}

	@Test
	public void testValidateWithValidInput() {
		ArrayList<String> input = new ArrayList<>();
		input.add("Hello");
		Echo e = new Echo(input, wd, oh);

		assertTrue(e.runCommand());

	}

	@Test
	public void testExecute() {
		ArrayList<String> inputArgs = new ArrayList<>();
		inputArgs.add("Hello");

		Echo e = new Echo(inputArgs, wd, oh);
		assertTrue(e.execute());
	}

	private ArrayList<String> populateArrayList(String[] input) {
		ArrayList<String> output = new ArrayList<>();
		for (String s : input) {
			output.add(s);
		}
		return output;

	}

}
