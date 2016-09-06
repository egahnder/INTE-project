package org.pucko.core;


import org.junit.Test;
import org.pucko.testutilities.InputBuilder;

import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class MenuTest {
	
	@Test
	public void testControllerIsCalledWhenUserGivesInput(){
		
		String input = "";
		InputBuilder inputBuilder = new InputBuilder();
		inputBuilder.addLine(input);
		InputStream inputStream = inputBuilder.build();
		Controller controller = mock(Controller.class);		
		Scanner scanner = new Scanner(inputStream);
		Menu menu = new Menu(controller, scanner);
		menu.run();
		verify(controller, times(1)).parseCommand(input);
	}
}
