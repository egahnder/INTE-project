package org.pucko.commands;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

import java.nio.file.Path;
import java.util.ArrayList;

import org.mockito.Mock;

public class PwdTest {

	@Mock
    private CommandUtils commandUtils;

	@Rule
	public TemporaryFolder testFolder = new TemporaryFolder();

	@Before
	public void setUp() {
        initMocks(this);
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
