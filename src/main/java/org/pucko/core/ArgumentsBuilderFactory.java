package org.pucko.core;

import org.pucko.commands.CommandArguments;
import org.pucko.commands.CommandArguments.ArgumentsBuilder;

public class ArgumentsBuilderFactory {
    public ArgumentsBuilder createBuilder(){
        return CommandArguments.builder();
    }
}
