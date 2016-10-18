package org.pucko.commands;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;


import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class CommandTest {


    @Mock
    private CommandUtils commandUtils;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testCommandHandleOutput() {
        Echo e = new Echo(commandUtils);
        e.handleOutput("test output");
        verify(commandUtils, times(1)).addArg("test output");


    }


}
