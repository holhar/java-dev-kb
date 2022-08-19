package de.holhar.java_dev_kb.designpatterns.structural.composite;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DrawingCompositeTest {

    @Test
    void drawingComposite() {
        Triangle triangleOne = new Triangle();
        Triangle triangleTwo = new Triangle();
        Circle circle = new Circle();

        DrawingComposite composite = new DrawingComposite();
        composite.add(triangleOne);
        composite.add(triangleTwo);
        composite.add(circle);
        composite.draw("Red");
        assertEquals(3, composite.size());


        composite.clear();
        assertEquals(0, composite.size());

        composite.add(triangleOne);
        composite.add(circle);
        composite.draw("Green");
        assertEquals(2, composite.size());
    }

}
