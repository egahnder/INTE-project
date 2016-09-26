package org.pucko.commands;

import org.pucko.commands.CommandUtils.UtilsBuilder;

/**
 * Created by eric on 2016-09-25.
 */
public class UtilsBuilderFactory {

    public UtilsBuilder createUtilsBuilder(){
        return CommandUtils.builder();
    }
}
