package org.pucko.commands;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
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
            Path tempDir = Files.createTempDirectory("tempFiles");
            
            //Then we create a new WorkingDirectory object with the old Path
            WorkingDirectory wd = createWorkingDirectory(tempDir);
            
            // Creating the Cd object
            Cd cd = new Cd(args, wd);
            
            // Cd changes the directory
            cd.execute();
            
            // This is the new Path to compare to the one Cd has changed
            Path newPath = Paths.get(tempDir.toString() +"/bar");
            
            // We compare our specially prepared path with the one WorkingDirectory returns
            assertEquals(newPath, wd.getPath());
        
    }
    
    public WorkingDirectory createWorkingDirectory(Path path) {
        
        return new WorkingDirectory(path);
        
    }

}
