package de.holhar.java_dev_kb.designpatterns.structural.decorator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasicCar implements Car {

    private static final Logger logger = LoggerFactory.getLogger(BasicCar.class);

    @Override
    public void assemble() {
        logger.info("Basic Car assembled.");
    }
}
