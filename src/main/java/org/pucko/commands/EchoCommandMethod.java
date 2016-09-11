package org.pucko.commands;

import java.util.ArrayList;

import org.pucko.commands.Command;
import org.pucko.core.CommandMethod;
import org.pucko.core.WorkingDirectory;

public class EchoCommandMethod implements CommandMethod {
    ArrayList<String> args = new ArrayList<>();

    @Override
    public Command runMethod(String[] allArgs, WorkingDirectory wd) {
        for (String s : allArgs) {
            args.add(s);
        }
        
        Echo echo = new Echo(args, wd);
        
        return echo;
        
        
    }
}


