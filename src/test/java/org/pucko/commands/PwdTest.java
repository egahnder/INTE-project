package org.pucko.commands;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import static org.mockito.Mockito.*;

import java.nio.file.Path;
import java.util.ArrayList;

import org.pucko.core.OutputHandler;
import org.pucko.core.WorkingDirectory;

public class PwdTest {

	private WorkingDirectory wd;
	private OutputHandler oh;
	private ArrayList<String> input;

	@Rule
	public TemporaryFolder testFolder = new TemporaryFolder();

	@Before
	public void setUp() {
		wd = mock(WorkingDirectory.class);
		oh = mock(OutputHandler.class);
		input = new ArrayList<>();
	}

	@Test
	public void testExecuteSetsOutput() {
		Path tempDir = testFolder.getRoot().toPath();
		when(wd.getPath()).thenReturn(tempDir);
		Pwd p = new Pwd(input, wd, oh);
		p.execute();
		verify(oh, times(1)).handleOutput(tempDir.toAbsolutePath().toString());

	}

}
