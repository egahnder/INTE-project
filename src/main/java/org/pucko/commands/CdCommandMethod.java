package org.pucko.commands;

import java.util.ArrayList;

import org.pucko.core.OutputHandler;
import org.pucko.core.WorkingDirectory;

public class CdCommandMethod implements CommandMethod {
    ArrayList<String> args;


    @Override
    public Command runMethod(String[] allArgs, WorkingDirectory wd, OutputHandler oh) {
        args = new ArrayList<>();
        args.add(allArgs[0]);
        Cd cd = new Cd(args, wd, oh);

        return cd;
        
        
    }

}
