package de.holhar.java_dev_kb.training.ocp8.ch02_design_patterns_and_principles.sec03_polymorphism_implementation.subsec01_object_and_reference_distinguish;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

/**
 * @author hhs@dasburo.com
 */
public class Lemur extends Primate implements HasTail {

    public int age = 10;

    @Override
    public boolean isTailStriped() {
        return false;
    }

    /**
     * Only one object is created and referenced: lemur
     * If you use a variable to refer to an object, then only the methods or variables that are part of the variable's
     * reference type can be called without an explicit cast.
     */
    public static void main(String[] args) {
        Lemur lemur = new Lemur();
        println(lemur.age);

        HasTail hasTail = lemur;
        println(hasTail.isTailStriped());
        // print(hasTail.age) // DOES NOT COMPILE

        Primate primate = lemur;
        println(primate.hashHair());
        // print(primate.isTailStriped()) // DOES NOT COMPILE
    }
}
