package org.pucko.CommandProcessors;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.pucko.commands.Command;
import org.pucko.core.CommandFactory;
import org.pucko.core.OutputHandler;
import org.pucko.core.WorkingDirectory;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.Matchers.isNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by eric on 2016-09-16.
 */
public class DefaultProcessorTest {


    private DefaultProcessor processor;

    @Mock
    private CommandFactory factory;
    @Mock
    private WorkingDirectory workingDirectory;
    @Mock
    private OutputHandler outputHandler;

    @Before
    public void setUp(){
        initMocks(this);
        processor = new DefaultProcessor(factory);
    }

    @Test
    public void testProcessorCallsFactory(){
        processor.process("test command", workingDirectory, outputHandler);
        ArrayList<String> args = new ArrayList<>(Arrays.asList("test command".split(" ")));
        verify(factory, times(1)).createCommand("test", args, workingDirectory, outputHandler);
    }
}