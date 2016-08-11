package helloWorld;

import org.junit.Test;

import static org.junit.Assert.*;

public class HelloWorldTest {

    @Test
    public void testHelloWorld() {
        HelloWorld greeting = new HelloWorld();
         assertEquals("Hello World",greeting.getHello());
    }
}
