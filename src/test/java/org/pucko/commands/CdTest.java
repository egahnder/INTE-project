package org.pucko.commands;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import static org.mockito.Mockito.*;
import org.pucko.core.OutputHandler;
import org.pucko.core.WorkingDirectory;

public class CdTest {

    private OutputHandler oh;
    private OutputHandler eh;
    private WorkingDirectory wd;
    private File testDir;
    private Path oldDir;
    private File newDir;
    private Path newPath;
    private ArrayList<String> args;

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    @Before
    public void setUp() throws Exception {
        oh = mock(OutputHandler.class);
        eh = mock(OutputHandler.class);
        testDir = testFolder.getRoot();
        oldDir = testDir.toPath();
        args = new ArrayList<>();
        wd = new WorkingDirectory(oldDir);

    }

    @Test
    public void testChildDir() throws IOException {

        args.add("bar");

        newDir = testFolder.newFolder(args.get(0));
        newPath = newDir.toPath();

        Cd cd = new Cd(args, wd, oh);

        cd.runCommand();

        assertEquals(newPath, wd.getPath());

    }

    @Test
    public void testParentDir() throws IOException {

        args.add("..");

        newPath = oldDir.getParent();

        Cd cd = new Cd(args, wd, oh);

        cd.runCommand();

        assertEquals(newPath, wd.getPath());

    }

    @Test
    public void testParentDirWhenInSystemRoot() throws IOException {

        setWorkingDirectoryPath("/");

        args.add("..");

        Cd cd = new Cd(args, wd, oh);

        // Make sure cd returns false since there are no directories above /
        assertEquals(false, cd.runCommand());

    }

    @Test
    public void testHomeDir() throws IOException {

        // Add the ~ (Tilde) symbol that corresponds to the user home directory to the ArrayList
        args.add("~");

        Cd cd = new Cd(args, wd, oh);
        cd.runCommand();

        String homePath = System.getProperty("user.home");
        Path newPath = Paths.get(homePath);

        assertEquals(newPath, wd.getPath());

    }

    @Test
    public void testSystemRoot() throws IOException {

        // Add the / symbol that corresponds to the System Root to the ArrayList
        args.add("/");

        Cd cd = new Cd(args, wd, oh);
        cd.runCommand();

        Path newPath = Paths.get("/");

        assertEquals(newPath, wd.getPath());

    }

    @Test
    public void testInvalidDir() throws IOException {

        args.add("invalidDir");

        Cd cd = new Cd(args, wd, oh);

        assertEquals(false, cd.runCommand());

    }

    @Test
    public void nullPathTest() {

        args.add(null);

        Cd cd = new Cd(args, wd, oh);

        assertEquals(false, cd.runCommand());

    }

    @Test
    public void printsErrorOnNoParam() {
        Cd cd = new Cd(args, wd, oh, eh);
        cd.runCommand();

        verify(eh, times(1)).handleOutput("ERROR: No argument provided");
    }

    private void setWorkingDirectoryPath(String path) {

        wd.changePath(Paths.get(path));
    }

}
