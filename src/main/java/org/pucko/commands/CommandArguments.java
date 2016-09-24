package org.pucko.commands;

import org.pucko.commands.CommandUtils.UtilsBuilder;
import org.pucko.core.InputHandler;
import org.pucko.core.OutputHandler;
import org.pucko.core.WorkingDirectory;

/**
 * Created by eric on 2016-09-24.
 */
public class CommandArguments {
    private InputHandler inputHandler;
    private OutputHandler outputHandler;
    private OutputHandler errorHandler;
    private WorkingDirectory workingDirectory;

    private CommandArguments(){}

    private CommandArguments(WorkingDirectory workingDirectory, OutputHandler outputHandler, OutputHandler errorHandler, InputHandler inputHandler) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
        this.workingDirectory = workingDirectory;
        this.errorHandler = errorHandler;
    }

    public static ArgumentsBuilder builder(){
        return new ArgumentsBuilder();
    }

    public UtilsBuilder transferData(UtilsBuilder utilsBuilder){
        utilsBuilder.addWorkingDirectory(workingDirectory);
        utilsBuilder.addErrorHandler(errorHandler);
        utilsBuilder.addOutputHandler(outputHandler);
        utilsBuilder.addInputHandler(inputHandler);
        return utilsBuilder;
    }

    public static class ArgumentsBuilder{
        private InputHandler inputHandler;
        private OutputHandler outputHandler;
        private OutputHandler errorHandler;
        private WorkingDirectory workingDirectory;

        public ArgumentsBuilder addWorkingDirectory(WorkingDirectory workingDirectory){
            this.workingDirectory = workingDirectory;
            return this;
        }

        public ArgumentsBuilder addOutputHandler(OutputHandler outputHandler){
            this.outputHandler = outputHandler;
            return this;
        }

        public ArgumentsBuilder addErrorHandler(OutputHandler errorHandler){
            this.errorHandler = errorHandler;
            return this;
        }

        public ArgumentsBuilder addInputHandler(InputHandler inputHandler){
            this.inputHandler = inputHandler;
            return this;
        }

        public CommandArguments build(){
            return new CommandArguments(workingDirectory, outputHandler, errorHandler, inputHandler);
        }

    }
}
