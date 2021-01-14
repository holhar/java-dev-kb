package de.holhar.java_dev_kb.training.ocp8.ch02_design_patterns_and_principles.sec02_functional_programming_intro.subsec01_functional_interface_design;

import de.holhar.java_dev_kb.training.ocp8.ch02_design_patterns_and_principles.sec05_design_patterns.subsec02_immutable_objects.Animal;

/**
 * @author hhs@dasburo.com
 */
public interface ValidFunctionalInterfaces {
}

/**
 * Introduces no new method, so still a valid functional interface.
 */
interface Run extends Sprint {
}

/**
 * Overrides the abstract method from its parent, so it's a valid functional interface.
 */
interface SprintFaster extends Sprint {
    public void sprint(Animal animal);
}

/**
 * The new methods are not abstract, so everything is still fine.
 */
interface Skip extends Sprint {

    public default int getHopCount(Kangoroo kangoroo) {
        return 10;
    }

    public static void skip(int speed) {
    }
}
