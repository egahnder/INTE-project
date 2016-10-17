package org.pucko.CommandProcessors;


import org.junit.Test;
import org.pucko.commands.Command;
import org.pucko.commands.CommandArguments;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.IsNot.not;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;

public class CommandProcessorTest {

    @Test
    public void testNoNextProcessorReturnsEmptyArray(){
        CommandProcessor commandProcessor = mock(CommandProcessor.class, CALLS_REAL_METHODS);
        ArrayList<Command> commandList = commandProcessor.sendToNextProcessor("Test", mock(CommandArguments.class));
        assertThat(commandList, is(not(nullValue())));
    }
}
