package de.holhar.java_dev_kb.designpatterns.structural.bridge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Notice the bridge between Shape and Color interfaces and use of composition in implementing the bridge pattern.
 */
public class Triangle extends Shape {

    private static final Logger logger = LoggerFactory.getLogger(Triangle.class);

    public Triangle(Color color) {
        super(color);
    }

    @Override
    public void applyColor() {
        logger.info("Triangle filled with color:");
        color.applyColor();
    }
}
