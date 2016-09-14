package org.pucko.core;

import org.pucko.commands.*;

import java.util.ArrayList;

public class CommandFactory {

    public Command createCommand(String command, ArrayList<String> args, WorkingDirectory workingDirectory, OutputHandler outputHandler){

        switch (command){
            case "echo":
                return new Echo(args, workingDirectory, outputHandler);
            case "pwd":
                return new Pwd(args, workingDirectory, outputHandler);
            case "cd":
                return new Cd(args, workingDirectory, outputHandler);
            default:
                return new DefaultCommand(args, workingDirectory, outputHandler);
        }
    }

}
