package de.holhar.java_dev_kb.training.ocp8.ch01_advanced_class_design.sec04_override_annotation;

/**
 * @author hhs@dasburo.com
 */
public class Bobcat {
    public void findDen() {
    }

    public void findAnotherDen() {
    }
}

class BobcatMother extends Bobcat {

    // @Override expresses the intention, that the programmer wants one method in the super class
    @Override
    public void findDen() {
    }

    // DOES NOT COMPILE - as this is overloading, not overriding => the compiler recognizes this
    // @Override
    public void findAnotherDen(boolean b) {

    }
}
