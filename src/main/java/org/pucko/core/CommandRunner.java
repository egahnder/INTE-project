package org.pucko.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.pucko.commands.Command;

public class CommandRunner {

    public void runCommands(ArrayList<Command> commands) {
        for (Command command : commands) {
            command.runCommand();

        }
    }
}
