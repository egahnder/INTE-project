package org.pucko.core;


import org.pucko.core.*;
import org.junit.Test;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class MenuTest {
	
	@Test
	public void testControllerIsCalledWhenUserGivesInput(){
		String input = "cd\n";
		createInputStream(input);
		Controller controller = mock(Controller.class);		
		Scanner scanner = new Scanner(System.in);
		Menu menu = new Menu(controller, scanner);
		menu.run();
		verify(controller, times(1)).parseCommand(any(String.class));
	}
	

	private void createInputStream(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }
}
