package de.holhar.java_dev_kb.training.ocp8.ch02_design_patterns_and_principles.sec02_functional_programming_intro.subsec01_functional_interface_design;

import de.holhar.java_dev_kb.training.ocp8.ch01_advanced_class_design.sec03_virtual_method_invocation.Animal;

/**
 * @author hhs@dasburo.com
 */
public interface InvalidFunctionalInterfaces {
}

/**
 * Invalid, because there's no method at all
 */
interface Walk {
}

/**
 * Invalid, as it adds another abstract method
 */
interface Dance extends Sprint {
    public void dance(Animal animal);
}

/**
 * Invalid, cause there are two distinctive abstract methods
 */
interface Crawl {
    public void crawl();
    public int getCount();
}
