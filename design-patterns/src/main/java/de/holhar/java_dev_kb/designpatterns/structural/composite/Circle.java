package de.holhar.java_dev_kb.designpatterns.structural.composite;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Leaf component - implements base component and form building block for composite. We can create multiple leaf
 * objects such as Triangle, Circle etc.
 */
public class Circle implements Shape {

    private static final Logger logger = LoggerFactory.getLogger(Circle.class);

    @Override
    public void draw(String fillColor) {
        logger.info("DrawingComposite Circle with color '{}'", fillColor);
    }
}
