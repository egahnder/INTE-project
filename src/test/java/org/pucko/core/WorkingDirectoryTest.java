package org.pucko.core;

import static org.junit.Assert.*;

import java.nio.file.Path;

import org.junit.Test;

public class WorkingDirectoryTest {
	
	
	@Test
	public void testPath() {
		String path = "/tmp/foo";
		WorkingDirectory wd = new WorkingDirectory(path);
	}

	@Test
	public void testGetPath() {
		String path = "/tmp/foo";
		WorkingDirectory wd = createWD(path);
		Path p = wd.getPath();
		assertEquals(path, p.toString());
	}
	
	@Test
	public void testChangePath() {
		String oldPath = "/tmp/foo";
		WorkingDirectory wd = createWD(oldPath);
		String newPath = "/tmp/bar";
		wd.changePath(newPath);
		
		assertEquals(newPath, wd.getPath().toString());
		
		
	}
	
	private WorkingDirectory createWD(String path) {
		WorkingDirectory wd = new WorkingDirectory(path);
		return wd;
	}
	


}
