package org.pucko.core;

import java.util.Scanner;

public class Menu {
	Scanner scanner;
	Controller controller;
	
	
	public Menu(Controller controller, Scanner scanner){
		this.scanner = scanner;
		this.controller = controller;
	}

	public void run() {
		while (true) {
			if (scanner.hasNextLine()) {
				String input = scanner.nextLine();
				controller.parseCommand(input);				
			}
		}		
	}
}
