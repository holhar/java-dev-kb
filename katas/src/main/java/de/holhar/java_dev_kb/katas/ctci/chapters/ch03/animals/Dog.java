package de.holhar.java_dev_kb.katas.ctci.chapters.ch03.animals;

public class Dog extends Animal {

    private final String name;

    public Dog(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
