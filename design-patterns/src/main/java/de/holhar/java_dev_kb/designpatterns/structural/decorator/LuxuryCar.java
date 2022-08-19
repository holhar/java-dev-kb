package de.holhar.java_dev_kb.designpatterns.structural.decorator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LuxuryCar extends CarDecorator {

    private static final Logger logger = LoggerFactory.getLogger(LuxuryCar.class);

    public LuxuryCar(Car car) {
        super(car);
    }

    @Override
    public void assemble() {
        super.assemble();
        logger.info("Added features of luxury car.");
    }
}
