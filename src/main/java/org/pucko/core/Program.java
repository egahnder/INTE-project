package org.pucko.core;

import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		CommandFactory commandFactory = new CommandFactory();
		CommandParser commandParser = new CommandParser(commandFactory);
		CommandRunner commandRunner = new CommandRunner();
		WorkingDirectory workingDirectory = new WorkingDirectory();
		Controller controller = new Controller(workingDirectory, commandRunner, commandParser);
		Menu menu = new Menu(controller, new Scanner(System.in));
		menu.run();
	}

}