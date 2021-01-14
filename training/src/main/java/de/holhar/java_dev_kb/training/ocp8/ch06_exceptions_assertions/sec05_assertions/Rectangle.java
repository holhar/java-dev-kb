package de.holhar.java_dev_kb.training.ocp8.ch06_exceptions_assertions.sec05_assertions;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

/**
 * @author hhs@dasburo.com
 * <p>
 * Assertions can, should, and probably will be turned off in a production environment
 * They should be used in DEV env to validate:
 * - internal invariants
 * - class invariants
 * - control flow invariants
 * - preconditions
 * - post conditions
 */
public class Rectangle {

    private int width;
    private int height;

    public Rectangle(int width, int height) {
        /*
         * DO NOT USE ASSERTIONS TO CHECK VALID ARGUMENTS PASSED IN TO A METHOD.
         * Use an IllegalArgumentException instead.
         */
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException();
        }
        this.height = height;
        this.width = width;
    }

    public static void main(String[] args) {
        Rectangle one = new Rectangle(5, 12);
        Rectangle two = new Rectangle(-4, 10);
        println("Area one = " + one.getArea());
        println("Area two = " + two.getArea());
    }

    public int getArea() {
        // Validating a class invariant:
        assert isValid() : "Not a valid rectangle";
        return width * height;
    }

    private boolean isValid() {
        return width > 0 && height > 0;
    }
}
