package de.holhar.java_dev_kb.training.ocp8.ch03_generics_and_collections.sec04_comparator_comparable.subsec01_comparable;

/**
 * @author hhs@dasburo.com
 */
public class Animal implements Comparable<Animal> {

    private int id;

    public Animal(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(Animal a) {
        return id - a.id;
    }
}
