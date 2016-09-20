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

    @Test
    public void testVerifyExecutableReturnsFalseWhenArgIsNegative() {

        String[] input = {"history", "-1"};

        History h = new History(populateArrayList(input), workingDirectory, outputHandler, errorHandler, inputHandler);
        assertFalse(h.verifyExecutable());

    }

    @Test
    public void testVerifyExecutableReturnsFalseWhenArgIsZero() {
        String[] input = {"history", "0"};

        History h = new History(populateArrayList(input), workingDirectory, outputHandler, errorHandler, inputHandler);
        assertFalse(h.verifyExecutable());

    }

    @Test
    public void testVerifyExecutableReturnsFalseWhenArgIsGreaterThanHistoryArraySize() {

        String[] input = {"history", "5"};
        History h = new History(populateArrayList(input), workingDirectory, outputHandler, errorHandler, inputHandler);

        h.verifyExecutable();

        when(inputHandler.getHistory()).thenReturn(mockArray);
        when(mockArray.size()).thenReturn(4);

        verify(errorHandler, times(1)).handleOutput("Number greater than command history");
        assertFalse(h.verifyExecutable());
    }


    @Test
    public void testVerifyExecutableReturnsTrueWhenArgIsEqualToHistoryArraySize() {

        String[] input = {"history", "3"};
        History h = new History(populateArrayList(input), workingDirectory, outputHandler, errorHandler, inputHandler);

        h.verifyExecutable();

        when(inputHandler.getHistory()).thenReturn(mockArray);
        when(mockArray.size()).thenReturn(3);
        assertTrue(h.verifyExecutable());


    }

    @Test
    public void testVerifyExecutableReturnsTrueWithNoArg() {

        String[] input = {"history"};
        History h = new History(populateArrayList(input), workingDirectory, outputHandler, errorHandler, inputHandler);

        assertTrue(h.verifyExecutable());

    }



    private ArrayList<String> populateArrayList(String[] input) {
        ArrayList<String> output = new ArrayList<>();
        Collections.addAll(output, input);
        return output;

    }
}
