package org.pucko.core;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu implements OutputHandler, InputHandler {
	private final Scanner scanner;
	private final Controller controller;
    private final ArrayList<String> history;


	public Menu(Controller controller, Scanner scanner) {
		this.scanner = scanner;
		this.controller = controller;
        history = new ArrayList<>();
	}

	public void run() {
		System.out.print(controller.getPrompt());
		while (scanner.hasNextLine()) {

			String input = scanner.nextLine();
            addHistory(input);
			if ("exit".equals(input)) {
				System.exit(0);
			}
			controller.parseCommand(input, this, this);
			System.out.print(controller.getPrompt());
		}
	}

	private void addHistory(String input){
    history.add(input);

	}




	@Override
	public void handleOutput(String output) {
		System.out.println(output);
	}


    @Override
    public ArrayList<String> getHistory() {
        return history;
    }
}
