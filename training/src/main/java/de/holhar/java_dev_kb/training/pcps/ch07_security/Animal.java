package de.holhar.java_dev_kb.training.pcps.ch07_security;

public class Animal {

    private final String type;
    private final String name;
    private final double weight;

    public Animal(String type, String name, double weight) {
        this.type = type;
        this.name = name;
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }
}
