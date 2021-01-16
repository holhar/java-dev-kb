package de.holhar.java_dev_kb.training.ocp8.ch02_design_patterns_and_principles.sec02_functional_programming_intro.subsec01_functional_interface_design;

import de.holhar.java_dev_kb.training.ocp8.ch01_advanced_class_design.sec03_virtual_method_invocation.Animal;

/**
 * Valid functional interface as it contains exactly one abstract method.
 */
@FunctionalInterface
public interface Sprint {

    public void sprint(Animal animal);
}
