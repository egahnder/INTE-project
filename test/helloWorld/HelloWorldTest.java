package helloWorld;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import helloWorld.HelloWorld;

public class HelloWorldTest {
    @Test
    public void testHelloWorld() {
        HelloWorld greeting = new HelloWorld();
        assertThat("Hello World", is(equalTo(greeting.getHello())));
    }
}
