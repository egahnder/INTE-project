package org.pucko.core;

import org.pucko.CommandProcessors.CommandProcessor;
import org.pucko.CommandProcessors.DefaultProcessor;
import org.pucko.CommandProcessors.PipeProcessor;
import org.pucko.commands.UtilsBuilderFactory;

import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		CommandFactory commandFactory = new CommandFactory();
        UtilsBuilderFactory utilsBuilderFactory = new UtilsBuilderFactory();
		CommandProcessor pipeProcessor = new PipeProcessor(commandFactory, utilsBuilderFactory);
        pipeProcessor.setNextProcessor(new DefaultProcessor(commandFactory, utilsBuilderFactory));
		CommandParser commandParser = new CommandParser(pipeProcessor);
		CommandRunner commandRunner = new CommandRunner();
		WorkingDirectory workingDirectory = new WorkingDirectory();
		Controller controller = new Controller(commandRunner, commandParser);
		ArgumentsBuilderFactory argumentsBuilderFactory = new ArgumentsBuilderFactory();
		Menu menu = new Menu(controller, workingDirectory, argumentsBuilderFactory);
		menu.run();
	}

}