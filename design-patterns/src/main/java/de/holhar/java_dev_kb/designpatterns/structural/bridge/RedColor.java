package de.holhar.java_dev_kb.designpatterns.structural.bridge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RedColor implements Color {

    private static final Logger logger = LoggerFactory.getLogger(RedColor.class);

    @Override
    public void applyColor() {
        logger.info("red.");
    }
}
