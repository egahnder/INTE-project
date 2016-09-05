package org.pucko.core;

import org.junit.Test;

public class WorkingDirectoryTest {
	
	
	@Test
	public void testPath() {
		String path = "/tmp/foo";
		WorkingDirectory wd = new WorkingDirectory(path);
	}

	@Test
	public void test() {
		WorkingDirectory wd = new WorkingDirectory();
	}


}
