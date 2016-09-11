package org.pucko.commands;

import java.util.ArrayList;

import org.pucko.core.CommandMethod;
import org.pucko.core.WorkingDirectory;

public class CdCommandMethod implements CommandMethod {
    ArrayList<String> args = new ArrayList<>();


    @Override
    public Command runMethod(String[] allArgs, WorkingDirectory wd) {
        args.add(allArgs[0]);
        Cd cd = new Cd(args, wd);
        
        return cd;
        
        
    }

}
