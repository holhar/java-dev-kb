package de.holhar.java_dev_kb.designpatterns.structural.flyweight;

import java.util.EnumMap;
import java.util.Map;

/*
 * Used by client programs to instantiate the objects - so we need to keep a map of the objects in the factory.
 * Whenever clients call to get an instance of the objects, it should be returned form the HashMap.
 * If not found then create a new object and put in the map, before returning it.
 *
 * Make sure to consider all intrinsic properties while creating the objects
 */
public class FlyweightShapeFactory {

    public enum ShapeType {
        OVAL_FILL, OVAL_NO_FILL, LINE
    }

    private static final Map<ShapeType, Shape> shapes = new EnumMap<>(ShapeType.class);

    public static Shape getShape(ShapeType type) {
        Shape shapeImpl = shapes.get(type);

        if (shapeImpl == null) {
            if (type.equals(ShapeType.OVAL_FILL)) {
                shapeImpl = new Oval(true);
            } else if (type.equals(ShapeType.OVAL_NO_FILL)) {
                shapeImpl = new Oval(false);
            } else if (type.equals(ShapeType.LINE)) {
                shapeImpl = new Line();
            }
            shapes.put(type, shapeImpl);
        }
        return shapeImpl;
    }
}
