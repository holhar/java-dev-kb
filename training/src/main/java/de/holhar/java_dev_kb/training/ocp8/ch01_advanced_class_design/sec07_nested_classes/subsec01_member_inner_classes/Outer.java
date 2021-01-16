package de.holhar.java_dev_kb.training.ocp8.ch01_advanced_class_design.sec07_nested_classes.subsec01_member_inner_classes;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

/**
 * A member inner class is a class defined at the same level as instance variables. It is not static. Often, this is
 * just referred to as an inner class without explicitly saying the type.
 *
 * Benefits of inner classes in general:
 * - They can encapsulate helper classes by restricting them to the containing class
 * - They can make it easy to create a class that will be used in only one place
 * - They can make the code easier to read (but also harder...)
 *
 * Member Inner Classes properties:
 * - Can be declared public, private, or protected or use default access
 * - Can extend any class and implement interfaces
 * - Can be abstract or final
 * - Cannot declare static fields or methods
 * - Can access members of the outer class including private members
 */
public class Outer {
    private String greeting = "Hi";

    public static void main(String[] args) {
        println("Calling Inner for the first time:");
        Outer outer = new Outer();
        outer.callInner();

        println("Calling Inner for the second time:");
        // There's another way to instantiate Inner - we need an instance of Outer in order to create Inner:
        Inner inner = outer.new Inner();
        inner.go();
    }

    public void callInner() {
        Inner inner = new Inner();
        inner.go();
    }

    protected class Inner {
        public int repeat = 3;

        public void go() {
            for (int i = 0; i < repeat; i++) {
                println(greeting);
            }
        }
    }
}
