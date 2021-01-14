package de.holhar.java_dev_kb.training.ocp8.ch03_generics_and_collections.sec04_comparator_comparable.subsec02_comparator;

/**
 * @author hhs@dasburo.com
 */
public class Duck implements Comparable<Duck> {

    private String name;
    private int weight;

    public Duck(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Duck d) {
        return name.compareTo(d.name);
    }
}
