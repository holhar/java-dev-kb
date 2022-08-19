package de.holhar.java_dev_kb.designpatterns.structural.composite;

/*
 * Base component - defines the common methods for leaf and composites
 */
public interface Shape {
    void draw(String fillColor);
}
