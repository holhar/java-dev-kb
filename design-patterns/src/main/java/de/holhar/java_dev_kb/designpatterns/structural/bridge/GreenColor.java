package de.holhar.java_dev_kb.designpatterns.structural.bridge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GreenColor implements Color {

    private static final Logger logger = LoggerFactory.getLogger(GreenColor.class);

    @Override
    public void applyColor() {
        logger.info("green.");
    }
}
