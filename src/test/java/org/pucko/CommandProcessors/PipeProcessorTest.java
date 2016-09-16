package org.pucko.CommandProcessors;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.pucko.commands.Command;
import org.pucko.core.CommandFactory;
import org.pucko.core.OutputHandler;
import org.pucko.core.WorkingDirectory;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.mockito.Matchers.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class PipeProcessorTest {


    private PipeProcessor processor;

    @Mock
    private CommandFactory commandFactory;
    @Mock
    private Command firstCommand;
    @Mock
    private Command secondCommand;
    @Mock
    private WorkingDirectory workingDirectory;
    @Mock
    private OutputHandler outputHandler;

    @Before
    public void setUp(){
        initMocks(this);
        processor = new PipeProcessor(commandFactory);
    }

    @Test
    public void testOnePipeReturnsTwoCommands(){
        ArrayList<Command> commands = processor.process("test1 | test2", workingDirectory, outputHandler);
        assertThat(commands.size(), is(2));
    }
}
