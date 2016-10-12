package org.pucko.core;

import org.pucko.commands.*;

public class CommandFactory {

    public Command createCommand(String command, CommandUtils commandUtils){

        switch (command){
            case "echo":
                return new Echo(commandUtils);
            case "pwd":
                return new Pwd(commandUtils);
            case "cd":
                return new Cd(commandUtils);
            case "history":
                return new History(commandUtils);
            case "touch":
                return new Touch(commandUtils);

            default:
                return new DefaultCommand(commandUtils);
        }
    }

}
