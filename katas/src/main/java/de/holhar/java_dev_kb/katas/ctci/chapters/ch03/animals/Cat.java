package de.holhar.java_dev_kb.katas.ctci.chapters.ch03.animals;

public class Cat extends Animal {

    private final String name;

    public Cat(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
