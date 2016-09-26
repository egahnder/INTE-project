package org.pucko.commands;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

import java.nio.file.Path;
import java.util.ArrayList;

import org.mockito.Mock;
import org.pucko.core.InputHandler;
import org.pucko.core.OutputHandler;
import org.pucko.core.WorkingDirectory;

public class PwdTest {

	@Mock
    private CommandUtils commandUtils;
	private ArrayList<String> input;

	@Rule
	public TemporaryFolder testFolder = new TemporaryFolder();

	@Before
	public void setUp() {
        initMocks(this);
		input = new ArrayList<>();
	}

	@Test
	public void testExecuteSetsOutput() {
		Path tempDir = testFolder.getRoot().toPath();
		when(commandUtils.getWorkingDirectory()).thenReturn(tempDir);
		Pwd p = new Pwd(commandUtils);
		p.execute();
		verify(commandUtils, times(1)).output(tempDir.toAbsolutePath().toString());

	}

}
