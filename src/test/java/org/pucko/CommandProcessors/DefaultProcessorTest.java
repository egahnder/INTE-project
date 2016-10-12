package org.pucko.CommandProcessors;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.pucko.commands.CommandArguments;
import org.pucko.commands.UtilsBuilderFactory;
import org.pucko.core.CommandFactory;

import static org.mockito.MockitoAnnotations.initMocks;

public class DefaultProcessorTest {


    private DefaultProcessor processor;

    @Mock
    private CommandFactory factory;
    @Mock
    private CommandArguments commandArguments;
    @Mock
    private UtilsBuilderFactory utilsBuilderFactory;

    @Before
    public void setUp(){
        initMocks(this);
        processor = new DefaultProcessor(factory, utilsBuilderFactory);
    }

    @Test
    public void testProcessorCallsFactory(){
//        Make a factory
//        processor.process("test command", commandArguments);
//        ArrayList<String> args = new ArrayList<>(Arrays.asList("test command".split(" ")));
//        verify(factory, times(1)).createCommand("test", );
    }
}