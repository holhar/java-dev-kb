package de.holhar.java_dev_kb.training.ocp8.ch01_advanced_class_design.sec07_nested_classes.subsec02_local_inner_classes;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

/**
 * @author hhs@dasburo.com
 * <p>
 * A local inner class is defined within a method.
 * <p>
 * Local Inner Classes properties:
 * - They do not have an access specifier
 * - They cannot be declared static and cannot declare static fields or methods
 * - They have access to all fields and methods of the enclosing class
 * - They do not have access to local variables of a method unless those variables are final or effectively final
 */
public class Outer {

    private int length = 5;

    public static void main(String[] args) {
        Outer outer = new Outer();
        outer.calculate();
    }

    public void calculate() {
        final int width = 20;

        /*
         * The compiler is generating a separate class file from this inner class -> a separate class has no way to
         * reference a local variable. If the local variable is final, Java can handle it by passing it to the
         * constructor of the inner class or by storing it in the class file. If it weren't effectively final, these
         * tricks wouldn't work because the value could change after the copy was made. Since Java 8 the "effectively
         * final" concept was introduced -> if the code could still compile with the keyword final inserted before
         * the local variable, the variable is effectively final.
         */
        class Inner {
            public void multiply() {
                println(length * width);
            }
        }

        Inner inner = new Inner();
        inner.multiply();
    }
}
