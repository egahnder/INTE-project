package org.pucko.core;

import java.util.ArrayList;

import org.pucko.commands.Command;

public class CommandRunner {

    public void runCommands(ArrayList<Command> commands) {
        commands.forEach(Command::runCommand);
    }
}
