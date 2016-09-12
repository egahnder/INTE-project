package org.pucko.commands;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
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
    
    OutputHandler oh;
    
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    @Before
    public void setUp() throws Exception {
        oh = mock(OutputHandler.class);
    }

    @Test
    public void testChangeDirDown() throws IOException {
        
            // Create the ArrayList with the new path string
            ArrayList<String> args = new ArrayList<>();
            args.add("bar");
            
            File testDir = testFolder.getRoot();
            
            Path oldDir = testDir.toPath();
            
            File newDir = testFolder.newFolder(args.get(0));
            
            Path newPath = newDir.toPath();
            
            //Then we create a new WorkingDirectory object with the old Path
            WorkingDirectory wd = createWorkingDirectory(oldDir);
            
            // Creating the Cd object
            Cd cd = new Cd(args, wd, oh);
            
            // Cd changes the directory
            cd.execute();
            
            // We compare our specially prepared path with the one WorkingDirectory returns
            assertEquals(newPath, wd.getPath());
        
    }
    
    @Test
    public void testParentDir() throws IOException {
        
        // Create the ArrayList with the new .. indication we want to move up one level in the hiearchy
        ArrayList<String> args = new ArrayList<>();
        args.add("..");
        
        File testDir = testFolder.getRoot();
        
        Path oldDir = testDir.toPath();
        
        // This is the new Path to compare to the one Cd has changed
        Path newPath = oldDir.getParent();
        
        //Then we create a new WorkingDirectory object with the old Path
        WorkingDirectory wd = createWorkingDirectory(oldDir);
        
        // Creating the Cd object
        Cd cd = new Cd(args, wd, oh);
        
        // Cd changes the directory
        cd.execute(); 
        
        // We compare our specially prepared path with the one WorkingDirectory returns
        assertEquals(newPath, wd.getPath());
        
    }
    
    @Test
    public void testHomeDir() throws IOException {
        
        // Create the ArrayList with the ~ (Tilde) symbol that corresponds to the user home directory
        ArrayList<String> args = new ArrayList<>();
        args.add("~");
        
        File testDir = testFolder.getRoot();
        
        Path oldDir = testDir.toPath();
        
        //Then we create a new WorkingDirectory object with the old Path
        WorkingDirectory wd = createWorkingDirectory(oldDir);
        
        // Creating the Cd object
        Cd cd = new Cd(args, wd, oh);
        
        // Cd changes the directory
        cd.execute();
        
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
        
        // Lets create a temporary directory in system "temp" dir
        
        File testDir = testFolder.getRoot();
        
        Path oldDir = testDir.toPath();
  
        //Then we create a new WorkingDirectory object with the old Path
        WorkingDirectory wd = createWorkingDirectory(oldDir);
        
        // Creating the Cd object
        Cd cd = new Cd(args, wd, oh);
        
        // We make sure cd.execute return false since the directory does not exist.
        
        assertEquals(false, cd.execute());
           
    }
    
    @Test
    public void nullPathTest() {
     // Create the ArrayList with the invalid Dir
        ArrayList<String> args = new ArrayList<>();
        args.add(null);
        
        
        File testDir = testFolder.getRoot();
        
        Path oldDir = testDir.toPath();
        
        //Then we create a new WorkingDirectory object with the old Path
        WorkingDirectory wd = createWorkingDirectory(oldDir);
        
        // Creating the Cd object
        Cd cd = new Cd(args, wd, oh);
        
        // Cd changes the directory
        boolean executedOk = cd.execute();
        
        // We make sure cd.execute return false since the directory argument is null;
        
        assertEquals(false, executedOk);
        
    }
    
    @Test
    public void forceValidateTest() throws IOException {
     // Create the ArrayList with the invalid Dir
        ArrayList<String> args = new ArrayList<>();
        args.add("foo");
        
        File testDir = testFolder.getRoot();
        
        Path oldDir = testDir.toPath();
        
        File newDir = testFolder.newFolder("foo");
        
        Path newPath = newDir.toPath();
        
        
        //Then we create a new WorkingDirectory object with the old Path
        WorkingDirectory wd = createWorkingDirectory(oldDir);
        
        // Creating the Cd object
        Cd cd = new Cd(args, wd, oh);
        
        // We make sure cd.validate() return true since the directory is valid;
        
        assertEquals(true, cd.validate());
        
    }
    
    
    public WorkingDirectory createWorkingDirectory(Path path) {
        
        return new WorkingDirectory(path);
        
    }

}
