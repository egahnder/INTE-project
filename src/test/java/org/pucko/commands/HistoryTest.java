package org.pucko.commands;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import static org.mockito.InOrder.*;

import org.mockito.Mock;
import org.pucko.core.InputHandler;
import org.pucko.core.OutputHandler;
import org.pucko.core.WorkingDirectory;

import java.util.ArrayList;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by Tobias on 20/09/16.
 */
public class HistoryTest {

    //git push origin feature/develop-history

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

        commandHistory = new ArrayList<>();
        commandHistory.add("cd ..");
        commandHistory.add("pwd");
        commandHistory.add("history 4");
        commandHistory.add("touch test.fil");
        commandHistory.add("echo Hello World");
        commandHistory.add("history");

    }



    @Test
    public void testExecutePrintsFirstCommand() {

        String[] input = {"history", "1"};
        History h = new History(populateArrayList(input), workingDirectory, outputHandler, errorHandler, inputHandler);

        when(inputHandler.getHistory()).thenReturn(commandHistory);

        h.runCommand();

        verify(outputHandler, times(1)).handleOutput("1\tcd ..");
    }

    @Test
    public void testExecutePrintsLastCommand() {

        String[] input = {"history", "6"};
        History h = new History(populateArrayList(input), workingDirectory, outputHandler, errorHandler, inputHandler);

        when(inputHandler.getHistory()).thenReturn(commandHistory);

        h.runCommand();
        verify(outputHandler, times(1)).handleOutput("1\tcd ..");
        verify(outputHandler, times(1)).handleOutput("2\tpwd");
        verify(outputHandler, times(1)).handleOutput("3\thistory 4");
        verify(outputHandler, times(1)).handleOutput("4\ttouch test.fil");
        verify(outputHandler, times(1)).handleOutput("5\techo Hello World");
        verify(outputHandler, times(1)).handleOutput("6\thistory");
    }

    @Test
    public void testExecutePrintsCompleteHistoryArray() {

        String[] input = {"history"};
        History h = new History(populateArrayList(input), workingDirectory, outputHandler, errorHandler, inputHandler);

        when(inputHandler.getHistory()).thenReturn(commandHistory);

        h.runCommand();
        verify(outputHandler, times(1)).handleOutput("1\tcd ..");
        verify(outputHandler, times(1)).handleOutput("2\tpwd");
        verify(outputHandler, times(1)).handleOutput("3\thistory 4");
        verify(outputHandler, times(1)).handleOutput("4\ttouch test.fil");
        verify(outputHandler, times(1)).handleOutput("5\techo Hello World");

    }


    //TEST 1 input == "-1"
    @Test
    public void testVerifyExecutableReturnsFalseWhenArgIsNegative() {

        String[] input = {"history", "-1"};

        History h = new History(populateArrayList(input), workingDirectory, outputHandler, errorHandler, inputHandler);
        assertFalse(h.runCommand());

    }

    //TEST 2 input == "0"
    @Test
    public void testVerifyExecutableReturnsFalseWhenArgIsZero() {
        String[] input = {"history", "0"};
        History h = new History(populateArrayList(input), workingDirectory, outputHandler, errorHandler, inputHandler);

        assertFalse(h.runCommand());
    }


    //TEST 3 input == "1"
    @Test
    public void testVerifyExecutableReturnsTrueWhenArgIsPositive() {
        String[] input = {"history", "1"};
        History h = new History(populateArrayList(input), workingDirectory, outputHandler, errorHandler, inputHandler);

        when(inputHandler.getHistory()).thenReturn(commandHistory);

        assertTrue(h.runCommand());
    }

    //TEST 4 input == history.size()
    @Test
    public void testVerifyExecutableReturnsTrueWhenArgIsEqualToHistoryArraySize() {

        String[] input = {"history", "5"};
        History h = new History(populateArrayList(input), workingDirectory, outputHandler, errorHandler, inputHandler);

        when(inputHandler.getHistory()).thenReturn(commandHistory);
        assertTrue(h.runCommand());


    }

    //TEST 5 input == history.size() + 1
    @Test
    public void testVerifyExecutableReturnsFalseWhenArgGreaterThanCommandHistory() {

        String[] input = {"history", "7"};
        History h = new History(populateArrayList(input), workingDirectory, outputHandler, errorHandler, inputHandler);

        when(inputHandler.getHistory()).thenReturn(commandHistory);

        assertFalse(h.runCommand());
    }


    //TEST 6 input == NaN
    @Test
    public void testVerifyExecutableReturnsFalseWithCharArg() {

        String[] input = {"history", "hello"};
        History h = new History(populateArrayList(input), workingDirectory, outputHandler, errorHandler, inputHandler);

        assertFalse(h.runCommand());
    }


    //TEST 7 input == empty
    @Test
    public void testVerifyExecutableReturnsTrueWithNoArg() {

        String[] input = {"history"};
        History h = new History(populateArrayList(input), workingDirectory, outputHandler, errorHandler, inputHandler);

        assertTrue(h.runCommand());

    }


    //TEST 8 input == "1", "3"
    @Test
    public void testVerifyExecutableReturnsFalseWhenTooManyArgs() {

        String[] input = {"history", "1", "3"};
        History h = new History(populateArrayList(input), workingDirectory, outputHandler, errorHandler, inputHandler);

        when(inputHandler.getHistory()).thenReturn(commandHistory);
        assertFalse(h.runCommand());
    }

    //TEST 9
    @Test
    public void testVerifyExecutableReturnsFalseWhenEmptyCommandHistory() {

        String[] input = {"history", "1"};
        History h = new History(populateArrayList(input), workingDirectory, outputHandler, errorHandler, inputHandler);

        ArrayList<String> emptyCommandHistory = new ArrayList<>();
        emptyCommandHistory.add("history");
        when(inputHandler.getHistory()).thenReturn(emptyCommandHistory);

        assertFalse(h.runCommand());
    }


    //Error Message Tests

    @Test
    public void testErrorMsgWhenArgNegative() {
        String[] input = {"history", "-1"};
        History h = new History(populateArrayList(input), workingDirectory, outputHandler, errorHandler, inputHandler);

        h.runCommand();
        verify(errorHandler, times(1)).handleOutput("Command number has to be > 0");
    }

    @Test
    public void testErrorMsgWhenArgGreaterThanCommandHistory() {
        String[] input = {"history", "6"};
        History h = new History(populateArrayList(input), workingDirectory, outputHandler, errorHandler, inputHandler);

        h.runCommand();
        verify(errorHandler, times(1)).handleOutput("Number greater than command history");
    }

    @Test
    public void testErrorMsgWhenArgNaN() {
        String[] input = {"history", "hello"};
        History h = new History(populateArrayList(input), workingDirectory, outputHandler, errorHandler, inputHandler);

        h.runCommand();
        verify(errorHandler, times(1)).handleOutput("Only numbers are accepted");
    }

    @Test
    public void testErrorMsgWhenTooManyArgs() {
        String[] input = {"history", "1", "3"};
        History h = new History(populateArrayList(input), workingDirectory, outputHandler, errorHandler, inputHandler);

        h.runCommand();
        verify(errorHandler, times(1)).handleOutput("Too many arguments");
    }

    @Test
    public void testErrorMsgWhenEmptyCommandHistory() {

        String[] input = {"history", "1"};
        History h = new History(populateArrayList(input), workingDirectory, outputHandler, errorHandler, inputHandler);

        ArrayList<String> emptyCommandHistory = new ArrayList<>();
        emptyCommandHistory.add("history");
        when(inputHandler.getHistory()).thenReturn(emptyCommandHistory);

        h.runCommand();

        verify(errorHandler, times(1)).handleOutput("No command history available");
    }


    private ArrayList<String> populateArrayList(String[] input) {
        ArrayList<String> output = new ArrayList<>();
        Collections.addAll(output, input);
        return output;

    }


}
