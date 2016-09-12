package org.pucko.core;

import java.util.Scanner;

public class Menu implements OutputHandler {
	Scanner scanner;
	Controller controller;

	public Menu(Controller controller, Scanner scanner) {
		this.scanner = scanner;
		this.controller = controller;
	}

	public void run() {
		String promt = controller.getPrompt();
		System.out.print(promt+" ");
		while (scanner.hasNextLine()) {
			String input = scanner.nextLine();
			if (input.equals("exit")) {
				System.exit(0);
			}
			controller.parseCommand(input, this);
		}
	}

	@Override
	public void handleOutput(String output) {
		System.out.println(output);
	}
}
