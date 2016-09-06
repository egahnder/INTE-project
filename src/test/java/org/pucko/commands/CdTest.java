package org.pucko.commands;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.pucko.core.WorkingDirectory;

public class CdTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testChangeDirDown() throws IOException {
        
            // Create the ArrayList with the new path string
            ArrayList<String> args = new ArrayList<>();
            args.add("bar");
            
            // Lets create a temporary directory in system "temp" dir
            // Lets also create the directory bar within it
            Path tempDir = Files.createTempDirectory("tempFiles");
            Path newPath = Files.createDirectory(tempDir.resolve(Paths.get("bar")));
            
            // And lets make sure to delete the tempFolder on exit
            tempDir.toFile().deleteOnExit();
            newPath.toFile().deleteOnExit();
            
            //Then we create a new WorkingDirectory object with the old Path
            WorkingDirectory wd = createWorkingDirectory(tempDir);
            
            // Creating the Cd object
            Cd cd = new Cd(args, wd);
            
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
        
        // Lets create a temporary directory in system "temp" dir
        Path tempDir = Files.createTempDirectory("tempFiles");
        
        // And lets make sure to delete the tempFolder on exit
        tempDir.toFile().deleteOnExit();
        
        //Then we create a new WorkingDirectory object with the old Path
        WorkingDirectory wd = createWorkingDirectory(tempDir);
        
        // Creating the Cd object
        Cd cd = new Cd(args, wd);
        
        // Cd changes the directory
        cd.execute();
        
        // This is the new Path to compare to the one Cd has changed
        Path newPath = tempDir.getParent();
        
        // We compare our specially prepared path with the one WorkingDirectory returns
        assertEquals(newPath, wd.getPath());
        
        
        
    }
    
    @Test
    public void testHomeDir() throws IOException {
        
        // Create the ArrayList with the ~ (Tilde) symbol that corresponds to the user home directory
        ArrayList<String> args = new ArrayList<>();
        args.add("~");
        
        // Lets create a temporary directory in system "temp" dir
        Path tempDir = Files.createTempDirectory("tempFiles");
   
        // And lets make sure to delete the tempFolder on exit
        tempDir.toFile().deleteOnExit();
        
        //Then we create a new WorkingDirectory object with the old Path
        WorkingDirectory wd = createWorkingDirectory(tempDir);
        
        // Creating the Cd object
        Cd cd = new Cd(args, wd);
        
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
        
        // Lets create a temporary directory in system "temp" dir
        Path tempDir = Files.createTempDirectory("tempFiles");
        
        Path invalidDir = tempDir.resolve("invalidDirectory");
        
        boolean isRegularReadableFile = Files.isRegularFile(invalidDir) &
                Files.isReadable(invalidDir);
        
        if (isRegularReadableFile) {
            try {
                Files.delete(invalidDir);
            } catch (NoSuchFileException x) {
                System.err.format("%s: no such" + " file or directory%n", invalidDir);
            } catch (DirectoryNotEmptyException x) {
                System.err.format("%s not empty%n", invalidDir);
            } catch (IOException x) {
                // File permission problems are caught here.
                System.err.println(x);
            }
        }
        
        // Create the ArrayList with the invalid Dir
        ArrayList<String> args = new ArrayList<>();
        args.add("invalidDir");
        
   
        // And lets make sure to delete the tempFolder on exit
        tempDir.toFile().deleteOnExit();
        
        //Then we create a new WorkingDirectory object with the old Path
        WorkingDirectory wd = createWorkingDirectory(tempDir);
        
        // Creating the Cd object
        Cd cd = new Cd(args, wd);
        
        // Cd changes the directory
        boolean executedOk = cd.execute();
        
        // We make sure cd.execute return false since the directory does not exist.
        
        assertEquals(false, executedOk);
        
        
        
    }
    
    
    
    
    public WorkingDirectory createWorkingDirectory(Path path) {
        
        return new WorkingDirectory(path);
        
    }

}
