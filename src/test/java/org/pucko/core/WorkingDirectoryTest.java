package org.pucko.core;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

public class WorkingDirectoryTest {


    @Test
    public void testPath() {
        Path path = Paths.get("/tmp/foo");
        WorkingDirectory wd = new WorkingDirectory(path);
    }

    @Test
    public void testGetPath() {
        String newPath = "/tmp/foo";
        Path path = Paths.get(newPath);
        WorkingDirectory wd = createWD(path);
        Path p = wd.getPath();
        assertEquals(newPath, p.toString());
    }

    @Test
    public void testChangePath() {
        Path oldPath = Paths.get("/tmp/foo");
        WorkingDirectory wd = createWD(oldPath);
        String newPath = "/tmp/bar";
        Path replacementPath = Paths.get(newPath);
        wd.changePath(replacementPath);

        assertEquals(newPath, wd.getPath().toString());


    }

    private WorkingDirectory createWD(Path path) {
        WorkingDirectory wd = new WorkingDirectory(path);
        return wd;
    }



}
