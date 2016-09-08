package org.pucko.commands;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import static org.mockito.Mockito.*;

import java.nio.file.Path;
import java.util.ArrayList;

import org.pucko.core.WorkingDirectory;

public class PwdTest {

	private WorkingDirectory wd;
	private ArrayList<String> input;

	@Rule
	public TemporaryFolder testFolder = new TemporaryFolder();

	@Before
	public void setUp() {
		wd = mock(WorkingDirectory.class);
		input = new ArrayList<>();
	}

	// Pwd ska bara köra om args är 0 eller null. args.size() > 0 blir false i
	// validate()

	@Test
	public void testExecuteSetsOutput() {
		Path tempDir = testFolder.getRoot().toPath();
		when(wd.getPath()).thenReturn(tempDir);
		Pwd p = new Pwd(input, wd);
		p.execute();
		assertEquals(tempDir.toAbsolutePath().toString(), p.getOutput());
	}

	@Test
	public void testValidateReturnTrue() {
		Pwd p = new Pwd(input, wd);
		assertTrue(p.validate());

	}

	@Test
	public void testValidateReturnFalse() {
		Pwd p = new Pwd(input, null);
		assertFalse(p.validate());

	}

}
