package de.holhar.java_dev_kb.designpatterns.creational.prototype;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

class CarPrototypeTest {

    private static final Logger logger = LoggerFactory.getLogger(CarPrototypeTest.class);

    @Test
    void test() throws CloneNotSupportedException {
        logger.info("*** Prototype Pattern Demo ***");
        Nano nano = new Nano("Green Nano");
        nano.setBasePrice(200_000);

        Ford ford = new Ford("Yellow Ford");
        ford.setBasePrice(500_000);

        // Nano
        CarPrototype prototype1 = nano.clone();
        prototype1.calculateOnRoadPrice();
        logger.info("prototype1 initialized:\nCLONE: '{}'\nPROTO: '{}'", prototype1, nano);
        assertTrue(prototype1 instanceof Nano);
        assertNotEquals(prototype1, nano);

        // Ford
        CarPrototype prototype2 = ford.clone();
        prototype2.calculateOnRoadPrice();
        logger.info("prototype2 initialized:\nCLONE: '{}'\nPROTO: '{}'", prototype2, ford);
        assertTrue(prototype2 instanceof Ford);
        assertNotEquals(prototype2, ford);
    }
}
