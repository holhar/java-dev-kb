package de.holhar.java_dev_kb.designpatterns.structural.decorator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DecoratorApplication {

    private static final Logger LOGGER = LogManager.getLogger(DecoratorApplication.class);

    public static void main(String[] args) {
        LOGGER.debug("*** Decorator pattern demo ***");
        ConcreteComponent withoutDecorator = new ConcreteComponent();
        withoutDecorator.makeHouse();
        LOGGER.debug("________________________");

        LOGGER.debug("Apply floorDecorator");
        FloorDecorator floorDecorator = new FloorDecorator();
        floorDecorator.setComponent(withoutDecorator);
        floorDecorator.makeHouse();
        LOGGER.debug("________________________");

        LOGGER.debug("Apply paintDecorator");
        PaintDecorator paintDecorator = new PaintDecorator();
        paintDecorator.setComponent(floorDecorator);
        paintDecorator.makeHouse();
        LOGGER.debug("________________________");
    }
}
