package org.pucko.core;

import jdk.nashorn.internal.runtime.regexp.joni.constants.Arguments;
import org.junit.Test;
import org.pucko.commands.CommandArguments.ArgumentsBuilder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

/**
 * Created by eric on 2016-10-18.
 */
public class ArgumentsBuilderFactoryTest {

    @Test
    public void testFactoryReturnsBuilder(){
        ArgumentsBuilderFactory factory = new ArgumentsBuilderFactory();
        ArgumentsBuilder builder = factory.createBuilder();
        assertThat(builder, is(instanceOf(ArgumentsBuilder.class)));
    }

}
