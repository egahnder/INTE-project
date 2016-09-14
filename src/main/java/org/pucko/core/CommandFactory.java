package org.pucko.core;

import org.pucko.commands.Cd;
import org.pucko.commands.Command;
import org.pucko.commands.DefaultCommand;
import org.pucko.commands.Pwd;

import java.util.ArrayList;

public class CommandFactory {

    public Command createCommand(String command, ArrayList<String> args, WorkingDirectory workingDirectory, OutputHandler outputHandler){

        switch (command){
            case "pwd":
                return new Pwd(args, workingDirectory, outputHandler);
            case "cd":
                return new Cd(args, workingDirectory, outputHandler);
            default:
                return new DefaultCommand(args, workingDirectory, outputHandler);
        }
    }

}
