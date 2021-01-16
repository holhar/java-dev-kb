package de.holhar.java_dev_kb.training.ocp8.ch03_generics_and_collections.sec02_generics.subsec04_generic_interfaces;

/**
 * First approach: specify generic type to be processed by the class.
 */
public class ShippableRobotCrate implements Shippable<Robot> {

    public void ship(Robot robot) {
    }
}

class Robot {
}
