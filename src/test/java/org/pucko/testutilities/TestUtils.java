package org.pucko.testutilities;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import org.pucko.commands.CommandUtils;

import java.nio.file.Paths;

import static org.mockito.Mockito.when;

public class TestUtils {

    public static void setArgs(CommandUtils commandUtils, String...args) {
        Builder<String> listBuilder = ImmutableList.builder();
        for(String arg : args){
            listBuilder.add(arg);
        }
        when(commandUtils.getArgs()).thenReturn(listBuilder.build());
    }

    public static void setWorkingDirectory(CommandUtils commandUtils, String path) {

        when(commandUtils.getWorkingDirectory()).thenReturn(Paths.get(path));
    }

    public static void setHistory(CommandUtils commandUtils, String...args) {
        Builder<String> listBuilder = ImmutableList.builder();
        for(String arg : args){
            listBuilder.add(arg);
        }
        when(commandUtils.getHistory()).thenReturn(listBuilder.build());
    }
}
