package org.pucko.core;

import java.nio.file.Path;
import java.nio.file.Paths;

public class WorkingDirectory {

    private Path path;

    public WorkingDirectory(Path path) {
        this.path = path;
    }

    public Path getPath() {
        return path;
    }

    public void changePath(Path newPath) {
        this.path = newPath;
    }

}
