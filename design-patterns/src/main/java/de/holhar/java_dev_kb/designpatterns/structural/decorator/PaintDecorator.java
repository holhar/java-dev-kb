package de.holhar.java_dev_kb.designpatterns.structural.decorator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PaintDecorator extends AbstractDecorator {

    private static final Logger LOGGER = LogManager.getLogger(PaintDecorator.class);

    @Override
    public void makeHouse() {
        super.makeHouse();
        // Decorating
        paintHouse();
    }

    private void paintHouse() {
        LOGGER.debug("Painting the house");
    }
}
