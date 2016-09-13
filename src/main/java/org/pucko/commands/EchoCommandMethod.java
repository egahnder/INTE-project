package org.pucko.commands;

import java.util.ArrayList;

import org.pucko.commands.Command;
import org.pucko.core.OutputHandler;
import org.pucko.core.WorkingDirectory;

public class EchoCommandMethod implements CommandMethod {

    @Override
    public Command runMethod(String[] allArgs, WorkingDirectory wd, OutputHandler oh) {
        ArrayList<String> args = new ArrayList<>();
        for (String s : allArgs) {
            args.add(s);
        }
        
        Echo echo = new Echo(args, wd, oh);
        
        return echo;
        
        
    }
}


