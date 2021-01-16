package de.holhar.java_dev_kb.training.ocp8.ch02_design_patterns_and_principles.sec05_design_patterns.subsec04_factory;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class Fish extends Food {

    public Fish(int quantity) {
        super(quantity);
    }

    public void consumed() {
        println("Fish eaten: " + getQuantity());
    }
}
