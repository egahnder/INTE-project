package org.pucko.core;

import org.pucko.commands.*;

import java.util.ArrayList;

public class CommandFactory {

    public Command createCommand(String command, ArrayList<String> args, WorkingDirectory workingDirectory, OutputHandler outputHandler, InputHandler inputHandler){
        return createCommand(command, args, workingDirectory, outputHandler, outputHandler, inputHandler);
    }

    public Command createCommand(String command, ArrayList<String> args, WorkingDirectory workingDirectory, OutputHandler outputHandler, OutputHandler errorHandler, InputHandler inputHandler){

        switch (command){
            case "echo":
                return new Echo(args, workingDirectory, outputHandler, errorHandler, inputHandler);
            case "pwd":
                return new Pwd(args, workingDirectory, outputHandler, errorHandler, inputHandler);
            case "cd":
                return new Cd(args, workingDirectory, outputHandler, errorHandler, inputHandler);
            default:
                return new DefaultCommand(args, workingDirectory, outputHandler, errorHandler, inputHandler);
        }
    }

}
