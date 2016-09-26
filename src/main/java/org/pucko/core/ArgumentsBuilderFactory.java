package org.pucko.core;

import org.pucko.commands.CommandArguments;
import org.pucko.commands.CommandArguments.ArgumentsBuilder;

/**
 * Created by eric on 2016-09-25.
 */
public class ArgumentsBuilderFactory {
    public ArgumentsBuilder createBuilder(){
        return CommandArguments.builder();
    }
}
