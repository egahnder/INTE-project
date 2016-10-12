package org.pucko.core;

import org.pucko.commands.CommandArguments;
import org.pucko.commands.CommandArguments.ArgumentsBuilder;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu implements OutputHandler, InputHandler {
	private final Scanner scanner;
	private final Controller controller;
    private final ArrayList<String> history;
	private final WorkingDirectory workingDirectory;
    private final ArgumentsBuilderFactory argumentsBuilderFactory;


	public Menu(Controller controller, WorkingDirectory workingDirectory, ArgumentsBuilderFactory argumentsBuilderFactory) {
		this.scanner = new Scanner(System.in);
		this.controller = controller;
        this.workingDirectory = workingDirectory;
        this.argumentsBuilderFactory = argumentsBuilderFactory;
        history = new ArrayList<>();
	}

	public void run() {
		System.out.print(getPrompt());
		while (scanner.hasNextLine()) {

			String input = scanner.nextLine();
            addHistory(input);
			if ("exit".equals(input)) {
				System.exit(0);
			}
            ArgumentsBuilder argumentsBuilder = argumentsBuilderFactory.createBuilder();
            CommandArguments commandArguments = argumentsBuilder.addInputHandler(this)
                                                                .addWorkingDirectory(workingDirectory)
                                                                .addOutputHandler(this)
                                                                .addErrorHandler(this)
                                                                .build();
			controller.parseCommand(input, commandArguments);
			System.out.print(getPrompt());
		}
	}

	private void addHistory(String input){
    history.add(input);

	}

    private String getPrompt(){
        Path path = workingDirectory.getPath();
        String pathString = path.toString();
        String homeString = System.getProperty("user.home");
        if (pathString.startsWith(homeString)){
            pathString = pathString.replaceFirst(homeString, "~");
        }
        return pathString+"$ ";
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
