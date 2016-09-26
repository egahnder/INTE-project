package org.pucko.commands;

import com.google.common.collect.ImmutableList;
import org.pucko.core.InputHandler;
import org.pucko.core.OutputHandler;
import org.pucko.core.WorkingDirectory;

import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Created by eric on 2016-09-24.
 */
public class CommandUtils {
    private WorkingDirectory workingDirectory;
    private OutputHandler outputHandler;
    private OutputHandler errorHandler;
    private InputHandler inputHandler;
    private ArrayList<String> args;

    private CommandUtils(ArrayList<String> args, InputHandler inputHandler, OutputHandler errorHandler, OutputHandler outputHandler, WorkingDirectory workingDirectory) {
        this.workingDirectory = workingDirectory;
        this.outputHandler = outputHandler;
        this.errorHandler = errorHandler;
        this.inputHandler = inputHandler;
        this.args = args;
    }

    public static UtilsBuilder builder(){
        return new UtilsBuilder();
    }

    public Path getWorkingDirectory() {
        return workingDirectory.getPath();
    }

    public void changeWorkingDirectory(Path path) {
        workingDirectory.changePath(path);
    }

    public void output(String output) {
        outputHandler.handleOutput(output);
    }

    public void error(String error) {
        errorHandler.handleOutput(error);
    }

    public ImmutableList<String> getHistory() {
        return ImmutableList.copyOf(inputHandler.getHistory());
    }

    public ImmutableList<String> getArgs() {
        return ImmutableList.copyOf(args);
    }

    public void addArg(String arg) {
        args.add(arg);
    }

    public static class UtilsBuilder{
        private WorkingDirectory workingDirectory;
        private OutputHandler outputHandler;
        private OutputHandler errorHandler;
        private InputHandler inputHandler;
        private ArrayList<String> args;

        public UtilsBuilder addWorkingDirectory(WorkingDirectory workingDirectory){
            this.workingDirectory = workingDirectory;
            return this;
        }
        public UtilsBuilder addErrorHandler(OutputHandler errorHandler){
            this.errorHandler = errorHandler;
            return this;
        }
        public UtilsBuilder addOutputHandler(OutputHandler outputHandler){
            this.outputHandler = outputHandler;
            return this;
        }
        public UtilsBuilder addInputHandler(InputHandler inputHandler){
            this.inputHandler = inputHandler;
            return this;
        }

        public UtilsBuilder addArgs(ArrayList<String> args){
            this.args = args;
            return this;
        }
        public CommandUtils build(){
            return new CommandUtils(args, inputHandler, errorHandler, outputHandler, workingDirectory);
        }
    }
}
