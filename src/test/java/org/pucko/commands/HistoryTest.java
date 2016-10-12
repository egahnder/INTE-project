package org.pucko.commands;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import org.mockito.Mock;
import org.pucko.core.InputHandler;
import org.pucko.core.OutputHandler;
import org.pucko.core.WorkingDirectory;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.pucko.testutilities.TestUtils.setArgs;
import static org.pucko.testutilities.TestUtils.setHistory;

public class HistoryTest {

    //git push origin feature/develop-history
    
    @Mock
    private CommandUtils commandUtils;
    @Mock
    private WorkingDirectory workingDirectory;
    @Mock
    private OutputHandler outputHandler;
    @Mock
    private OutputHandler errorHandler;
    @Mock
    private InputHandler inputHandler;
    private ArrayList<String> commandHistory;

    @Before
    public void setUp() {
        initMocks(this);
        setHistory(commandUtils, "cd ..", "pwd", "history 4", "touch test.fil", "echo Hello World", "history");
    }



    @Test
    public void testExecutePrintsFirstCommand() {

        setArgs(commandUtils, "history", "1");
        History h = new History(commandUtils);

        h.runCommand();

        verify(commandUtils, times(1)).output("1\tcd ..");
    }

    @Test
    public void testExecutePrintsLastCommand() {

        setArgs(commandUtils, "history", "6");
        History h = new History(commandUtils);

        when(inputHandler.getHistory()).thenReturn(commandHistory);

        h.runCommand();
        verify(commandUtils, times(1)).output("1\tcd ..");
        verify(commandUtils, times(1)).output("2\tpwd");
        verify(commandUtils, times(1)).output("3\thistory 4");
        verify(commandUtils, times(1)).output("4\ttouch test.fil");
        verify(commandUtils, times(1)).output("5\techo Hello World");
        verify(commandUtils, times(1)).output("6\thistory");
    }

    @Test
    public void testExecutePrintsCompleteHistoryArray() {

        setArgs(commandUtils, "history");
        History h = new History(commandUtils);

        when(inputHandler.getHistory()).thenReturn(commandHistory);

        h.runCommand();
        verify(commandUtils, times(1)).output("1\tcd ..");
        verify(commandUtils, times(1)).output("2\tpwd");
        verify(commandUtils, times(1)).output("3\thistory 4");
        verify(commandUtils, times(1)).output("4\ttouch test.fil");
        verify(commandUtils, times(1)).output("5\techo Hello World");

    }


    //TEST 1 input == "-1"
    @Test
    public void testVerifyExecutableReturnsFalseWhenArgIsNegative() {

        setArgs(commandUtils, "history", "-1");

        History h = new History(commandUtils);
        assertFalse(h.runCommand());

    }

    //TEST 2 input == "0"
    @Test
    public void testVerifyExecutableReturnsFalseWhenArgIsZero() {
        setArgs(commandUtils, "history", "0");
        History h = new History(commandUtils);

        assertFalse(h.runCommand());
    }


    //TEST 3 input == "1"
    @Test
    public void testVerifyExecutableReturnsTrueWhenArgIsPositive() {
        setArgs(commandUtils, "history", "1");
        History h = new History(commandUtils);

        when(inputHandler.getHistory()).thenReturn(commandHistory);

        assertTrue(h.runCommand());
    }

    //TEST 4 input == history.size()
    @Test
    public void testVerifyExecutableReturnsTrueWhenArgIsEqualToHistoryArraySize() {

        setArgs(commandUtils, "history", "5");
        History h = new History(commandUtils);

        when(inputHandler.getHistory()).thenReturn(commandHistory);
        assertTrue(h.runCommand());


    }

    //TEST 5 input == history.size() + 1
    @Test
    public void testVerifyExecutableReturnsFalseWhenArgGreaterThanCommandHistory() {

        setArgs(commandUtils, "history", "7");
        History h = new History(commandUtils);

        when(inputHandler.getHistory()).thenReturn(commandHistory);

        assertFalse(h.runCommand());
    }


    //TEST 6 input == NaN
    @Test
    public void testVerifyExecutableReturnsFalseWithCharArg() {

        setArgs(commandUtils, "history", "hello");
        History h = new History(commandUtils);

        assertFalse(h.runCommand());
    }


    //TEST 7 input == empty
    @Test
    public void testVerifyExecutableReturnsTrueWithNoArg() {

        setArgs(commandUtils, "history");
        History h = new History(commandUtils);

        assertTrue(h.runCommand());

    }


    //TEST 8 input == "1", "3"
    @Test
    public void testVerifyExecutableReturnsFalseWhenTooManyArgs() {

        setArgs(commandUtils, "history", "1", "3");
        History h = new History(commandUtils);

        when(inputHandler.getHistory()).thenReturn(commandHistory);
        assertFalse(h.runCommand());
    }

    //TEST 9
    @Test
    public void testVerifyExecutableReturnsFalseWhenEmptyCommandHistory() {
        reset(commandUtils);
        setArgs(commandUtils, "history");
        setHistory(commandUtils, "history");
        History h = new History(commandUtils);
        assertFalse(h.runCommand());
    }


    //Error Message Tests

    @Test
    public void testErrorMsgWhenArgNegative() {
        setArgs(commandUtils, "history", "-1");
        History h = new History(commandUtils);

        h.runCommand();
        verify(commandUtils, times(1)).error("Command number has to be > 0");
    }

    @Test
    public void testErrorMsgWhenArgGreaterThanCommandHistory() {
        setArgs(commandUtils, "history", "7");
        History h = new History(commandUtils);
        h.runCommand();
        verify(commandUtils, times(1)).error("Number greater than command history");
    }

    @Test
    public void testErrorMsgWhenArgNaN() {
        setArgs(commandUtils, "history", "hello");
        History h = new History(commandUtils);

        h.runCommand();
        verify(commandUtils, times(1)).error("Only numbers are accepted");
    }

    @Test
    public void testErrorMsgWhenTooManyArgs() {
        setArgs(commandUtils, "history", "1", "3");
        History h = new History(commandUtils);

        h.runCommand();
        verify(commandUtils, times(1)).error("Too many arguments");
    }

    @Test
    public void testErrorMsgWhenEmptyCommandHistory() {

        setArgs(commandUtils, "history", "1");
        History h = new History(commandUtils);

        setHistory(commandUtils, "history");

        h.runCommand();

        verify(commandUtils, times(1)).error("No command history available");
    }
}
