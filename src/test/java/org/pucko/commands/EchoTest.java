package org.pucko.commands;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.pucko.testutilities.TestUtils.setArgs;

public class EchoTest {
    
    private Echo echo;
    
    @Mock
    private CommandUtils commandUtils;

	@Before
	public void setUp() {
        initMocks(this);
        echo = new Echo(commandUtils);
	}

	@Test
	public void testExecuteSetsSingleWordOutput() {
        setArgs(commandUtils, "echo", "Hello");

		echo.execute();
		verify(commandUtils, times(1)).output("Hello");
	}

	@Test
	public void testExecuteSetsMultipleWordOutput() {
		setArgs(commandUtils, "echo", "Hello", "World!");

		echo.execute();
		verify(commandUtils, times(1)).output("Hello World!");

	}

	@Test
	public void testValidateWithZeroInput() {

		setArgs(commandUtils);
		assertFalse(echo.runCommand());

	}

	@Test
	public void testValidateWithValidInput() {
        setArgs(commandUtils, "echo",  "Hello");
		assertTrue(echo.runCommand());

	}

	@Test
	public void testExecute() {
        setArgs(commandUtils, "echo", "Hello");
		assertTrue(echo.execute());
	}

	@Test
	public void testEchoDosentRepeatEcho(){
	    setArgs(commandUtils, "echo", "test");
        echo.runCommand();
        verify(commandUtils, times(1)).output("test");
	}
}
