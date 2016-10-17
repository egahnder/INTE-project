package org.pucko.commands;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertFalse;
import static org.mockito.MockitoAnnotations.initMocks;

public class DefaultCommandTest {

    @Mock
    CommandUtils commandUtils;

    DefaultCommand defaultCommand;

    @Before
    public void setUp(){
        initMocks(this);
        defaultCommand = new DefaultCommand(commandUtils);
    }

    @Test
    public void testExecuteReturnsFalse(){
        assertFalse(defaultCommand.execute());
    }

    @Test
    public void testVerifyExecutableReturnsFalse(){
        assertFalse(defaultCommand.verifyExecutable());
    }


}
