package org.pucko.core;

import org.pucko.commands.Command;

// Implementation as suggested by paislee @
// https://stackoverflow.com/questions/9489474/java-run-method-based-on-user-input

public interface CommandMethod {

        public Command runMethod(String[] allArgs, WorkingDirectory wd);

}
