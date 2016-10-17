package org.pucko.commands;

import org.junit.Test;
import org.pucko.commands.CommandUtils.UtilsBuilder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

public class UtilsBuilderFactoryTest {
    @Test
    public void testBuilderIsReturned(){
        UtilsBuilderFactory utilsBuilderFactory = new UtilsBuilderFactory();
        assertThat(utilsBuilderFactory.createUtilsBuilder(), is(instanceOf(UtilsBuilder.class)));
    }
}
