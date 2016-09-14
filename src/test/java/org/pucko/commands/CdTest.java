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

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    private OutputHandler oh;
    private WorkingDirectory wd;
    private File testDir;
    private Path oldDir;
    private File newDir;
    private Path newPath;
    private ArrayList<String> args;

    @Before
    public void setUp() throws Exception {
        oh = mock(OutputHandler.class);
        testDir = testFolder.getRoot();
        oldDir = testDir.toPath();
        args = new ArrayList<>();
        wd = new WorkingDirectory(oldDir);

    }

    @Test
    public void testChangeDirDown() throws IOException {

        args.add("bar");

        newDir = testFolder.newFolder(args.get(0));
        newPath = newDir.toPath();

        // Creating the Cd object
        Cd cd = new Cd(args, wd, oh);

        // Cd changes the directory
        cd.runCommand();

        // We compare our specially prepared path with the one WorkingDirectory returns
        assertEquals(newPath, wd.getPath());

    }

    @Test
    public void testParentDir() throws IOException {

        args.add("..");


        // This is the new Path to compare to the one Cd has changed
        newPath = oldDir.getParent();

        // Creating the Cd object
        Cd cd = new Cd(args, wd, oh);

        // Cd changes the directory
        cd.runCommand();

        // We compare our specially prepared path with the one WorkingDirectory returns
        assertEquals(newPath, wd.getPath());

    }

    @Test
    public void testHomeDir() throws IOException {

        // Add the ~ (Tilde) symbol that corresponds to the user home directory to the ArrayList
        args.add("~");

        Cd cd = new Cd(args, wd, oh);
        cd.runCommand();

        // This is the new Path to compare to the one Cd has changed
        String homePath = System.getProperty("user.home");
        Path newPath = Paths.get(homePath);

        // We compare our specially prepared path with the one WorkingDirectory returns
        assertEquals(newPath, wd.getPath());



    }

    @Test
    public void tesInvalidDir() throws IOException {
        ArrayList<String> args = new ArrayList<>();
        args.add("invalidDir");

        Cd cd = new Cd(args, wd, oh);

        // We make sure cd.execute return false since the directory does not exist.

        assertEquals(false, cd.runCommand());

    }

    @Test
    public void nullPathTest() {

        args.add(null);

        Cd cd = new Cd(args, wd, oh);

        // We make sure cd.execute return false since the directory argument is null;

        assertEquals(false, cd.runCommand());

    }

}
