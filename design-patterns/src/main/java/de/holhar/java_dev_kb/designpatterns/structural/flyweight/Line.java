package de.holhar.java_dev_kb.designpatterns.structural.flyweight;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

public class Line implements Shape {

    private static final Logger logger = LoggerFactory.getLogger(Line.class);

    public Line() {
        logger.info("Creating Line object");
        // Adding time delay (simulate long object creation)
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics line, int x1, int y1, int x2, int y2, Color color) {
        line.setColor(color);
        line.drawLine(x1, y1, x2, y2);
    }
}
