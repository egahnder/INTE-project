
package org.pucko.commands;

/**
 * Created by Tobias on 15/09/16.
 */

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import static org.mockito.Mockito.*;

import java.io.File;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import org.pucko.core.OutputHandler;
import org.pucko.core.WorkingDirectory;
import org.pucko.testutilities.ArgsPopulator;
import org.junit.Before;
import org.junit.Rule;

public class TouchTest {

    private WorkingDirectory wd;
    private OutputHandler oh;
    private OutputHandler eh;
    private ArgsPopulator ap;

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    @Before
    public void setUp() {
        wd = mock(WorkingDirectory.class);
        oh = mock(OutputHandler.class);
        eh = mock(OutputHandler.class);
        ap = new ArgsPopulator();
    }


    @Test
    public void testExecuteCreatesSingleFile() throws IOException {

        Path folderPath = testFolder.getRoot().toPath();
        when(wd.getPath()).thenReturn(folderPath);

        String[] input = {"filnamn"};


        Touch t = new Touch(ap.populate(input), wd, oh, eh);

        Path filePath = folderPath.resolve("filnamn");
        t.execute();
        assertTrue(new File(filePath.toString()).isFile());

    }

    @Test
    public void testExecuteCreatesFileWithExtension() {

    }

}
