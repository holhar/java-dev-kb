package de.holhar.java_dev_kb.designpatterns.structural.composite;

import java.util.ArrayList;
import java.util.List;

/*
 * Composite object - contains group of leaf objects, and we should provide some helper methods to add or delete
 * leafs from the group.
 */
public class DrawingComposite implements Shape {

    // Collection of Shapes
    private final List<Shape> shapes = new ArrayList<>();

    @Override
    public void draw(String fillColor) {
        for (Shape s : shapes) {
            s.draw(fillColor);
        }
    }

    public void add(Shape s) {
        this.shapes.add(s);
    }

    public void remove(Shape s) {
        this.shapes.remove(s);
    }

    public void clear() {
        this.shapes.clear();
    }

    public int size() {
        return this.shapes.size();
    }
}
