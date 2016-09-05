package org.pucko.commands;

import static org.junit.Assert.*;

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
    public void testChangeDirDown() {
        
            ArrayList<String> args = new ArrayList<>();
            args.add("bar");
            
            Path oldPath = Paths.get("/tmp/foo");
            WorkingDirectory wd = createWorkingDirectory(oldPath);
            
            Cd cd = new Cd(args, wd);
            
           Path newPath = Paths.get("/tmp/foo/bar");
            
            cd.execute();

            assertEquals(newPath, wd.getPath().toString());
        
    }
    
    public WorkingDirectory createWorkingDirectory(Path path) {
        
        return new WorkingDirectory(path);
        
    }

}
