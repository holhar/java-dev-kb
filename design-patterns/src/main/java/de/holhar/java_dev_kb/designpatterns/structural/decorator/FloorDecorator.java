package de.holhar.java_dev_kb.designpatterns.structural.decorator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FloorDecorator extends AbstractDecorator {

    private static final Logger LOGGER = LogManager.getLogger(FloorDecorator.class);

    @Override
    public void makeHouse() {
        super.makeHouse();
        // Decorating
        addFloor();
    }

    private void addFloor() {
        LOGGER.debug("Add additional floor to house");
    }
}
