package org.pucko.core;

import java.nio.file.Path;
import java.nio.file.Paths;

public class WorkingDirectory {
	
	private Path path;
	
	public WorkingDirectory(String path) {
		this.path = Paths.get("/tmp/foo");
	}
	
	public Path getPath() {
		return path;
	}

}
