package org.pucko.core;

import java.util.Scanner;

public class Menu implements OutputHandler {
	private final Scanner scanner;
	private final Controller controller;

	public Menu(Controller controller, Scanner scanner) {
		this.scanner = scanner;
		this.controller = controller;
	}

	public void run() {
		System.out.print(controller.getPrompt());
		while (scanner.hasNextLine()) {

			String input = scanner.nextLine();
			if ("exit".equals(input)) {
				System.exit(0);
			}
			controller.parseCommand(input, this);
			System.out.print(controller.getPrompt());
		}
	}

	@Override
	public void handleOutput(String output) {
		System.out.println(output);
	}
}
