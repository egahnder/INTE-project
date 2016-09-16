package org.pucko.CommandProcessors;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;
import static org.mockito.MockitoAnnotations.initMocks;

public class CommandProcessorTest {

    private CommandProcessor commandProcessor;


    @Before
    public void setUp(){
        commandProcessor = mock(CommandProcessor.class, CALLS_REAL_METHODS);
    }

    @Test
    public void testSendToNextProcessorReturnsEmptyArrayList(){
        commandProcessor.sendToNextProcessor(any(), any(), any());
    }


}
