package org.pucko.core;

import org.pucko.CommandProcessors.CommandProcessor;
import org.pucko.CommandProcessors.DefaultProcessor;
import org.pucko.CommandProcessors.PipeProcessor;

import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		CommandFactory commandFactory = new CommandFactory();
		CommandProcessor pipeProcessor = new PipeProcessor(commandFactory);
        pipeProcessor.setNextProcessor(new DefaultProcessor(commandFactory));
		CommandParser commandParser = new CommandParser(pipeProcessor);
		CommandRunner commandRunner = new CommandRunner();
		WorkingDirectory workingDirectory = new WorkingDirectory();
		Controller controller = new Controller(commandRunner, commandParser);
		Menu menu = new Menu(controller, workingDirectory);
		menu.run();
	}

}