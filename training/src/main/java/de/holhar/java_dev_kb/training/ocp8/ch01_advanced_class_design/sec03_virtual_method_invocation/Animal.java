package de.holhar.java_dev_kb.training.ocp8.ch01_advanced_class_design.sec03_virtual_method_invocation;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public abstract class Animal {
    public abstract void feed();
}

class Cow extends Animal {
    public void feed() {
        addHay();
    }

    private void addHay() {
        println("Hay added");
    }
}

class Bird extends Animal {
    public void feed() {
        addSeed();
    }

    private void addSeed() {
        println("Seed added");
    }
}

class Lion extends Animal {
    public void feed() {
        addMeat();
    }

    private void addMeat() {
        println("Meat added");
    }
}