package org.pucko.core;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.pucko.commands.*;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.mockito.MockitoAnnotations.initMocks;



public class CommandFactoryTest {

    private CommandFactory commandFactory;

    @Mock
    private ArrayList<String> mockList;
    @Mock
    private WorkingDirectory workingDirectory;
    @Mock
    private OutputHandler outputHandler;
    @Mock
    private InputHandler inputHandler;
    @Mock
    private CommandUtils commandUtils;

    @Before
    public void setUp(){
        initMocks(this);
        commandFactory = new CommandFactory();
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

    @Test
    public void testCommandFactoryReturnsHistoryCommand(){
        testCommandIsCreated("history", History.class);
    }

    @Test
    public void testCommandFactoryReturnsTouchCommand(){
        testCommandIsCreated("touch", Touch .class);
    }


    private void testCommandIsCreated(String commandString, Class<?> commandClass) {
        Command command = commandFactory.createCommand(commandString, commandUtils);
        assertThat(command, is(instanceOf(commandClass)));
    }


}
