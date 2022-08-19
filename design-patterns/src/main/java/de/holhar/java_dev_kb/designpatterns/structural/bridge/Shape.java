package de.holhar.java_dev_kb.designpatterns.structural.bridge;

public abstract class Shape {

    // Composition - implementor
    protected Color color;

    // Constructor with implementor as input argument

    protected Shape(Color color) {
        this.color = color;
    }

    public abstract void applyColor();
}
