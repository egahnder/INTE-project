package org.pucko.commands;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.pucko.commands.CommandUtils.UtilsBuilder;

import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by eric on 2016-09-24.
 */
public class CommandArgumentsTest {

    @Mock
    private UtilsBuilder utilsBuilder;

    @Before
    public void setUp(){
        initMocks(this);
    }

    @Test
    public void testArgumentsReturnSame(){

    }
}
