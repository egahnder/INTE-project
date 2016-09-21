package org.pucko.commands;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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

    @Mock
    private WorkingDirectory workingDirectory;
    @Mock
    private OutputHandler outputHandler;
    @Mock
    private OutputHandler errorHandler;
    @Mock
    private InputHandler inputHandler;
    @Mock
    private ArrayList<String> mockArray;

    @Before
    public void setUp() {
        initMocks(this);
    }


    // input == 1
    @Test
    public void testExecutePrintsEarliestCommand() {

        String[] input = {"history", "1"};
        History h = new History(populateArrayList(input), workingDirectory, outputHandler, errorHandler, inputHandler);

        when(inputHandler.getHistory()).thenReturn(mockArray);
        when(mockArray.get(1)).thenReturn("pwd");

        h.execute();

        verify(outputHandler, times(1)).handleOutput("pwd");


    }


    //TEST 1 input == "-1"
    @Test
    public void testVerifyExecutableReturnsFalseWhenArgIsNegative() {

        String[] input = {"history", "-1"};

        History h = new History(populateArrayList(input), workingDirectory, outputHandler, errorHandler, inputHandler);
        assertFalse(h.verifyExecutable());

    }

    //TEST 2 input == "0"
    @Test
    public void testVerifyExecutableReturnsFalseWhenArgIsZero() {
        String[] input = {"history", "0"};

        History h = new History(populateArrayList(input), workingDirectory, outputHandler, errorHandler, inputHandler);
        assertFalse(h.verifyExecutable());

    }


    //TEST 3 input == "1"
    @Test
    public void testVerifyExecutableReturnsTrueWhenArgIsPositive() {
        String[] input = {"history", "1"};
        History h = new History(populateArrayList(input), workingDirectory, outputHandler, errorHandler, inputHandler);

        when(inputHandler.getHistory()).thenReturn(mockArray);
        when(mockArray.size()).thenReturn(1);

        assertTrue(h.verifyExecutable());


    }

    //TEST 4 input == args.size()
    @Test
    public void testVerifyExecutableReturnsTrueWhenArgIsEqualToHistoryArraySize() {

        String[] input = {"history", "3"};
        History h = new History(populateArrayList(input), workingDirectory, outputHandler, errorHandler, inputHandler);

        when(inputHandler.getHistory()).thenReturn(mockArray);
        when(mockArray.size()).thenReturn(3);
        assertTrue(h.verifyExecutable());


    }

    //TEST 5 input == args.size() + 1
    @Test
    public void testVerifyExecutableReturnsFalseWhenArgIsGreaterThanHistoryArraySize() {

        String[] input = {"history", "5"};
        History h = new History(populateArrayList(input), workingDirectory, outputHandler, errorHandler, inputHandler);

        boolean result = h.verifyExecutable();

        when(inputHandler.getHistory()).thenReturn(mockArray);
        when(mockArray.size()).thenReturn(4);

        verify(errorHandler, times(1)).handleOutput("Number greater than command history");
        assertFalse(result);
    }


    //TEST 6 input == characters
    @Test
    public void testVerifyExecutableReturnsFalseWithCharArg() {

        String[] input = {"history", "hello"};
        History h = new History(populateArrayList(input), workingDirectory, outputHandler, errorHandler, inputHandler);

        boolean result = h.verifyExecutable();

        verify(errorHandler, times(1)).handleOutput("Only numbers are accepted");

        assertFalse(result);

    }


    //TEST 7 input == empty
    @Test
    public void testVerifyExecutableReturnsTrueWithNoArg() {

        String[] input = {"history"};
        History h = new History(populateArrayList(input), workingDirectory, outputHandler, errorHandler, inputHandler);

        assertTrue(h.verifyExecutable());

    }


    //TEST 8 input == "1", "3"
    @Test
    public void testVerifyExecutableReturnsFalseWhenTooManyArgs() {

        String[] input = {"history", "1", "3"};
        History h = new History(populateArrayList(input), workingDirectory, outputHandler, errorHandler, inputHandler);

        when(inputHandler.getHistory()).thenReturn(mockArray);
        when(mockArray.size()).thenReturn(15);

        assertFalse(h.verifyExecutable());

    }


    private ArrayList<String> populateArrayList(String[] input) {
        ArrayList<String> output = new ArrayList<>();
        Collections.addAll(output, input);
        return output;

    }


}
