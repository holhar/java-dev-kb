package de.holhar.java_dev_kb.designpatterns.structural.flyweight;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

public class Oval implements Shape {

    private static final Logger logger = LoggerFactory.getLogger(Oval.class);

    // Intrinsic property
    private final boolean fill;

    public Oval(boolean fill) {
        logger.info("Creating Oval object with fill '{}'", fill);
        this.fill = fill;
        // Adding time delay (simulate long object creation)
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics circle, int x, int y, int width, int height, Color color) {
        circle.setColor(color);
        circle.drawOval(x, y, width, height);
        if (fill) {
            circle.fillOval(x, y, width, height);
        }
    }
}
