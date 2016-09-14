package org.pucko.core;

import org.pucko.commands.Command;
import org.pucko.commands.DefaultCommand;

import java.util.ArrayList;

public class CommandFactory {

    public Command createCommand(String command, ArrayList<String> args, WorkingDirectory workingDirectory, OutputHandler outputHandler){

        switch (command){
            default:
                return new DefaultCommand(args, workingDirectory, outputHandler);
        }
    }

}
