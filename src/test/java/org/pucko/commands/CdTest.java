package org.pucko.commands;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.pucko.testutilities.TestUtils.setArgs;
import static org.pucko.testutilities.TestUtils.setWorkingDirectory;

public class CdTest {

    @Mock
    private CommandUtils commandUtils;
    private Path oldDir;
    private File newDir;
    private Path newPath;
    private Cd cd;

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        File testDir = testFolder.getRoot();
        oldDir = testDir.toPath();

    }

    @Test
    public void testChildDir() throws IOException {
        setArgs(commandUtils, "cd", "bar");
        newDir = testFolder.newFolder("bar");
        newPath = newDir.toPath();
        setWorkingDirectory(commandUtils, oldDir.toString());
        cd = new Cd(commandUtils);
        cd.runCommand();
        verify(commandUtils, times(1)).changeWorkingDirectory(newPath);
    }

    @Test
    public void testParentDir() throws IOException {

        setArgs(commandUtils, "cd", "..");

        newPath = oldDir.getParent();
        setWorkingDirectory(commandUtils, oldDir.toString());
        cd = new Cd(commandUtils);
        cd.runCommand();
        verify(commandUtils, times(1)).changeWorkingDirectory(newPath);

    }

    @Test
    public void testParentDirWhenInSystemRoot() throws IOException {


        setArgs(commandUtils, "cd", "..");
        setWorkingDirectory(commandUtils, "/");
        cd = new Cd(commandUtils);

        // Make sure cd returns false since there are no directories above /
        assertEquals(false, cd.runCommand());

    }

    @Test
    public void testHomeDir() throws IOException {

        // Add the ~ (Tilde) symbol that corresponds to the user home directory to the ArrayList
        setArgs(commandUtils, "cd", "~");
        setWorkingDirectory(commandUtils, oldDir.toString());
        cd = new Cd(commandUtils);
        cd.runCommand();

        String homePath = System.getProperty("user.home");
        Path newPath = Paths.get(homePath);
        verify(commandUtils, times(1)).changeWorkingDirectory(newPath);

    }

    @Test
    public void testSystemRoot() throws IOException {

        // Add the / symbol that corresponds to the System Root to the ArrayList
        setArgs(commandUtils, "cd", "/");
        setWorkingDirectory(commandUtils, oldDir.toString());
        cd = new Cd(commandUtils);
        cd.runCommand();

        Path newPath = Paths.get("/");
        verify(commandUtils, times(1)).changeWorkingDirectory(newPath);
    }

    @Test
    public void testInvalidDir() throws IOException {

        setArgs(commandUtils, "cd", "invalidDir");
        setWorkingDirectory(commandUtils, oldDir.toString());
        cd = new Cd(commandUtils);
        assertEquals(false, cd.runCommand());

    }

    @Test
    public void testPrintsErrorOnPathDoesNotExist() {
        setArgs(commandUtils, "cd", "NonexistentDir");
        setWorkingDirectory(commandUtils, oldDir.toString());
        cd = new Cd(commandUtils);
        cd.runCommand();

        verify(commandUtils, times(1)).error("cd: No such file or directory: " +"NonexistentDir");
    }

    @Test
    public void testPrintsErrorOnDirectoryNotReadable() throws IOException {
        setArgs(commandUtils, "cd", "nonReadableDir");
        newDir = testFolder.newFolder("nonReadableDir");
        newDir.setReadable(false);
        setWorkingDirectory(commandUtils, oldDir.toString());
        cd = new Cd(commandUtils);
        cd.runCommand();

        verify(commandUtils, times(1)).error("cd: You do not have permission to access this directory");
    }

    @Test
    public void testDotStaysInSameDirectory() throws IOException {

        setArgs(commandUtils, "cd", ".");
        setWorkingDirectory(commandUtils, oldDir.toString());
        cd = new Cd(commandUtils);
        cd.runCommand();
        verify(commandUtils, times(1)).changeWorkingDirectory(oldDir);
    }
}