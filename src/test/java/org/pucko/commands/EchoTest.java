package org.pucko.commands;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.pucko.core.InputHandler;
import org.pucko.core.OutputHandler;
import org.pucko.core.WorkingDirectory;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collections;

public class EchoTest {

	private WorkingDirectory wd;
	private OutputHandler oh;
	private OutputHandler eh;
	private InputHandler ih;

	@Before
	public void setUp() {
		wd = mock(WorkingDirectory.class);
		oh = mock(OutputHandler.class);
        eh = mock(OutputHandler.class);
		ih = mock(InputHandler.class);
	}

	@Test
	public void testExecuteSetsSingleWordOutput() {
        String[] input = {"echo", "Hello"};
        ArrayList<String> inputArgs = populateArrayList(input);

		Echo e = new Echo(inputArgs, wd, oh, eh, ih);
		e.execute();
		verify(oh, times(1)).handleOutput("Hello");

	}

	@Test
	public void testExecuteSetsMultipleWordOutput() {
		String[] input = {"echo", "Hello", "World!" };
		ArrayList<String> inputArgs = populateArrayList(input);

		Echo e = new Echo(inputArgs, wd, oh, eh, ih);
		e.execute();
		verify(oh, times(1)).handleOutput("Hello World!");

	}

	@Test
	public void testValidateWithZeroInput() {

		ArrayList<String> input = new ArrayList<>();
		Echo e = new Echo(input, wd, oh, eh, ih);
		assertFalse(e.runCommand());

	}

	@Test
	public void testValidateWithValidInput() {
        String[] argsArray = {"echo",  "Hello"};
        ArrayList<String> inputArgs = populateArrayList(argsArray);
		Echo e = new Echo(inputArgs, wd, oh, eh, ih);

		assertTrue(e.runCommand());

	}

	@Test
	public void testExecute() {
        String[] argsArray = {"echo", "Hello"};
		ArrayList<String> inputArgs = populateArrayList(argsArray);
		Echo e = new Echo(inputArgs, wd, oh, eh, ih);
		assertTrue(e.execute());
	}

	@Test
	public void testEchoDosentRepeatEcho(){
	    String[] argsArray = {"echo", "test"};
        ArrayList<String> args = populateArrayList(argsArray);
        Command command = new Echo(args, wd, oh, eh, ih);
        command.runCommand();
        verify(oh, times(1)).handleOutput("test");
	}

	private ArrayList<String> populateArrayList(String[] input) {
		ArrayList<String> output = new ArrayList<>();
        Collections.addAll(output, input);
		return output;

	}

}
