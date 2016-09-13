package org.pucko.commands;

import org.pucko.core.OutputHandler;
import org.pucko.core.WorkingDirectory;

// Implementation as suggested by paislee @
// https://stackoverflow.com/questions/9489474/java-run-method-based-on-user-input

public interface CommandMethod {

        public Command runMethod(String[] allArgs, WorkingDirectory wd, OutputHandler oh);

}
