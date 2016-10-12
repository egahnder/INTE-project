package org.pucko.commands;

import org.pucko.commands.CommandUtils.UtilsBuilder;

public class UtilsBuilderFactory {

    public UtilsBuilder createUtilsBuilder(){
        return CommandUtils.builder();
    }
}
