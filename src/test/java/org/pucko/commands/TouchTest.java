
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
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import org.mockito.Mock;
import org.pucko.core.InputHandler;
import org.pucko.core.OutputHandler;
import org.pucko.core.WorkingDirectory;
import org.pucko.testutilities.ArgsPopulator;
import org.junit.Before;
import org.junit.Rule;

public class TouchTest {

    @Mock
    private WorkingDirectory workingDirectory;
    @Mock
    private OutputHandler outputHandler;
    @Mock
    private OutputHandler errorHandler;
    @Mock
    private InputHandler inputHandler;

    private ArgsPopulator argsPopulator;
    private Path folderPath;

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    @Before
    public void setUp() {
        initMocks(this);
        argsPopulator = new ArgsPopulator();
        folderPath = testFolder.getRoot().toPath();
    }

    @Test
    public void testCreatesSingleFile() throws IOException {

        when(workingDirectory.getPath()).thenReturn(folderPath);
        String[] input = {"touch", "filnamn"};
        Touch t = new Touch(argsPopulator.populate(input), workingDirectory, outputHandler, errorHandler, inputHandler);

        ArrayList<Path> filePathArray = createPathArray(input);
        t.runCommand();
        assertFiles(filePathArray);
    }

    @Test
    public void testCreatesTwoFiles() throws IOException {

        when(workingDirectory.getPath()).thenReturn(folderPath);
        String[] input = {"touch", "filnamn1", "filnamn2"};
        Touch t = new Touch(argsPopulator.populate(input), workingDirectory, outputHandler, errorHandler, inputHandler);

        ArrayList<Path> filePathArray = createPathArray(input);
        t.runCommand();
        assertFiles(filePathArray);
    }

    @Test
    public void testCreatesMultipleFiles() throws IOException {

        when(workingDirectory.getPath()).thenReturn(folderPath);
        String[] input = {"touch", "filnamn1", "filnamn2", "filnamn3", "filnamn4", "filnamn5", "filnamn6"};
        Touch t = new Touch(argsPopulator.populate(input), workingDirectory, outputHandler, errorHandler, inputHandler);

        ArrayList<Path> filePathArray = createPathArray(input);
        t.runCommand();
        assertFiles(filePathArray);
    }


    @Test
    public void testReturnsTrueWithOneFile() {

        when(workingDirectory.getPath()).thenReturn(folderPath);
        String[] input = {"touch", "filnamn"};
        Touch t = new Touch(argsPopulator.populate(input), workingDirectory, outputHandler, errorHandler, inputHandler);

        assertTrue(t.runCommand());

    }

    @Test
    public void testReturnsFalseWhenNoFileName() {
        when(workingDirectory.getPath()).thenReturn(folderPath);
        String[] input = {"touch"};
        Touch t = new Touch(argsPopulator.populate(input), workingDirectory, outputHandler, errorHandler, inputHandler);
        assertFalse(t.runCommand());
    }


    @Test
    public void testErrorMsgWhenNoFileName() {
        when(workingDirectory.getPath()).thenReturn(folderPath);
        String[] input = {"touch"};
        Touch t = new Touch(argsPopulator.populate(input), workingDirectory, outputHandler, errorHandler, inputHandler);
        t.runCommand();
        verify(errorHandler, times(1)).handleOutput("Filename is missing");
    }


    private ArrayList<Path> createPathArray(String[] input) {

        Path filePath;
        ArrayList<Path> filePathArray = new ArrayList<>();

        for (String s : input) {
            filePath = folderPath.resolve(s);
            filePathArray.add(filePath);
        }
        return filePathArray;
    }

    private void assertFiles(ArrayList<Path> filePathArray) {
        for (Path p : filePathArray) {
            assertTrue("File: " + p.toString(), new File(p.toString()).isFile());
        }

    }

}
