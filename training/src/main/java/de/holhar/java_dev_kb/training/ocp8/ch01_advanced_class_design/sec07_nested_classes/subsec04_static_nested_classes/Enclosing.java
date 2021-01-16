package de.holhar.java_dev_kb.training.ocp8.ch01_advanced_class_design.sec07_nested_classes.subsec04_static_nested_classes;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

/**
 * A static nested class is a static class that is defined at the same level as static variables.
 *
 * Static Nested Classes properties:
 * - The nesting creates a namespace because the enclosing class name must be used to refer to it.
 * - It can be made private or use one of the other access modifiers to encapsulate it.
 * - The enclosing class can refer to the fields and methods of the static nested class.
 */
public class Enclosing {

    public static void main(String[] args) {
        Nested nested = new Nested();
        println(nested.price);
    }

    static class Nested {
        private int price = 6;
    }
}
