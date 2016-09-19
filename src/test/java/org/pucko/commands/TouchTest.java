
package org.pucko.commands;

/**
 * Created by Tobias on 15/09/16.
 */

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import org.mockito.Mock;
import org.pucko.core.OutputHandler;
import org.pucko.core.WorkingDirectory;
import org.pucko.testutilities.ArgsPopulator;
import org.junit.Before;
import org.junit.Rule;

public class TouchTest {

    @Mock
    WorkingDirectory workingDirectory;
    OutputHandler outputHandler;
    OutputHandler errorHandler;
    ArgsPopulator argsPopulator;

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    @Before
    public void setUp() {
        initMocks(this);
        argsPopulator = new ArgsPopulator();
    }


    @Test
    public void testExecuteCreatesSingleFile() throws IOException {

        Path folderPath = testFolder.getRoot().toPath();
        when(workingDirectory.getPath()).thenReturn(folderPath);

        String[] input = {"filnamn"};


        Touch t = new Touch(argsPopulator.populate(input), workingDirectory, outputHandler, errorHandler);

        Path filePath = folderPath.resolve("filnamn");
        t.runCommand();
        assertTrue(new File(filePath.toString()).isFile());

    }

    @Test
    public void testExecuteCreatesTwoFiles() throws IOException {

        Path folderPath = testFolder.getRoot().toPath();
        when(workingDirectory.getPath()).thenReturn(folderPath);

        String[] input = {"filnamn1", "filnamn2"};

        Touch t = new Touch(argsPopulator.populate(input), workingDirectory, outputHandler, errorHandler);

        Path filePath1 = folderPath.resolve("filnamn1");
        Path filePath2 = folderPath.resolve("filnamn2");

        t.runCommand();
        assertTrue(new File(filePath1.toString()).isFile());
        assertTrue(new File(filePath2.toString()).isFile());

    }

    @Test
    public void testExecuteCreatesMultipleFiles() throws IOException {

        Path folderPath = testFolder.getRoot().toPath();
        when(workingDirectory.getPath()).thenReturn(folderPath);

        String[] input = {"filnamn1", "filnamn2", "filnamn3", "filnamn4", "filnamn5", "filnamn6"};

        Touch t = new Touch(argsPopulator.populate(input), workingDirectory, outputHandler, errorHandler);

        Path filePath1 = folderPath.resolve("filnamn1");
        Path filePath2 = folderPath.resolve("filnamn2");
        Path filePath3 = folderPath.resolve("filnamn3");
        Path filePath4 = folderPath.resolve("filnamn4");
        Path filePath5 = folderPath.resolve("filnamn5");
        Path filePath6 = folderPath.resolve("filnamn6");

        t.runCommand();

        assertTrue(new File(filePath1.toString()).isFile());
        assertTrue(new File(filePath2.toString()).isFile());
        assertTrue(new File(filePath3.toString()).isFile());
        assertTrue(new File(filePath4.toString()).isFile());
        assertTrue(new File(filePath5.toString()).isFile());
        assertTrue(new File(filePath6.toString()).isFile());
    }

    @Test
    public void textExecuteReturnsTrue() {


        //Lös ut skapandet av 1, 2 och 6 filer och anropa i testen.
        //t.runCommand() ska anropas.
    }

}
