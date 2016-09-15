package org.pucko.core;

import org.pucko.commands.*;

import java.util.ArrayList;

public class CommandFactory {

    public Command createCommand(String command, ArrayList<String> args, WorkingDirectory workingDirectory, OutputHandler outputHandler){
        return createCommand(command, args, workingDirectory, outputHandler, outputHandler);
    }

    private Command createCommand(String command, ArrayList<String> args, WorkingDirectory workingDirectory, OutputHandler outputHandler, OutputHandler errorHandler){

        switch (command){
            case "echo":
                return new Echo(args, workingDirectory, outputHandler, errorHandler);
            case "pwd":
                return new Pwd(args, workingDirectory, outputHandler, errorHandler);
            case "cd":
                return new Cd(args, workingDirectory, outputHandler, errorHandler);
            default:
                return new DefaultCommand(args, workingDirectory, outputHandler, errorHandler);
        }
    }

}
