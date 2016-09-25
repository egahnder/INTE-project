package org.pucko.CommandProcessors;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.pucko.commands.Command;
import org.pucko.commands.CommandArguments;
import org.pucko.commands.CommandUtils;
import org.pucko.commands.CommandUtils.UtilsBuilder;
import org.pucko.core.CommandFactory;
import org.pucko.core.InputHandler;
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
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by eric on 2016-09-16.
 */
public class DefaultProcessorTest {


    private DefaultProcessor processor;

    @Mock
    private CommandFactory factory;
    @Mock
    private CommandArguments commandArguments;

    @Before
    public void setUp(){
        initMocks(this);
        processor = new DefaultProcessor(factory);
    }

    @Test
    public void testProcessorCallsFactory(){
//        Make a factory
//        processor.process("test command", commandArguments);
//        ArrayList<String> args = new ArrayList<>(Arrays.asList("test command".split(" ")));
//        verify(factory, times(1)).createCommand("test", );
    }
}