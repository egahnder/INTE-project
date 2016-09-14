package org.pucko.core;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.pucko.commands.*;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;



public class CommandFactoryTest {

    @Mock
    private ArrayList<String> mockList;
    CommandFactory commandFactory;
    WorkingDirectory workingDirectory;
    OutputHandler outputHandler;

    @Before
    public void setUp(){
        initMocks(this);
        commandFactory = new CommandFactory();
        workingDirectory = mock(WorkingDirectory.class);
        outputHandler = mock(OutputHandler.class);
    }

    @Test
    public void testCommandFactoryReturnsDefaultCommand(){
        testCommandIsCreated("TestCommand", DefaultCommand.class);
    }


    @Test
    public void testCommandFactoryReturnsCdCommand(){
        testCommandIsCreated("cd", Cd.class);
    }

    @Test
    public void testCommandFactoryReturnsPwdCommand(){
        testCommandIsCreated("pwd", Pwd.class);
    }

    @Test
    public void testCommandFactoryReturnsEchoCommand(){
        testCommandIsCreated("echo", Echo.class);
    }

    private void testCommandIsCreated(String commandString, Class<?> commandClass) {
        Command command = commandFactory.createCommand(commandString, mockList, workingDirectory, outputHandler);
        assertThat(command, is(instanceOf(commandClass)));
    }


}
