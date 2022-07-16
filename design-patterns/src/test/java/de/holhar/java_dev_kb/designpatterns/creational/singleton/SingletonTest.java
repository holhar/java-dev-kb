package de.holhar.java_dev_kb.designpatterns.creational.singleton;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

class SingletonTest {

    private static final Logger logger = LoggerFactory.getLogger(SingletonTest.class);

    @Test
    void whenRetrievingSingletonInstanceMultipleTimesThenShouldReturnSameInstance() {
        logger.info("*** Singleton Pattern Demo ***");

        // Not applicable
        //Singleton instance = new Singleton();

        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();

        assertEquals(instance1, instance2);

        if (instance1 == instance2) {
            logger.info("instance1 and instance2 are the same instance");
        }
    }

}
