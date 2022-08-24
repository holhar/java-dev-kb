package de.holhar.java_dev_kb.designpatterns.misc.dependency_injection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyDIApplicationTest {

    private MessageServiceInjector injector;

    @BeforeEach
    void setup() {
        // Mock injector with anonymous class
        injector = () ->
                // Mock the message service
                (msg, rec) -> System.out.println("Mock message service implementation");
    }

    @Test
    void test() {
        Consumer consumer = injector.getConsumer();
        consumer.processMessages("Hello World!", "max@power.de");
    }

    @AfterEach
    void tearDown() {
        injector = null;
    }

}
