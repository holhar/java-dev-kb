package de.holhar.java_dev_kb.designpatterns.structural.flyweight;

import java.awt.*;

public interface Shape {
    void draw(Graphics g, int x, int y, int width, int height, Color color);
}
