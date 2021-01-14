package de.holhar.java_dev_kb.training.ocp8.ch03_generics_and_collections.sec04_searching_sorting;

import de.holhar.java_dev_kb.training.ocp8.ch03_generics_and_collections.sec04_comparator_comparable.subsec01_comparable.Duck;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author hhs@dasburo.com
 */
public class UseTreeSet {

    static class Rabbit { int id; }

    // Sorting does not check if the affected class implements Comparable at compile time:
    public static void main(String[] args) {
        Set<Duck> ducks = new TreeSet<>();
        ducks.add(new Duck("Puddles")); // Throws no exception
        Set<Rabbit> rabbits = new TreeSet<>();
        // rabbits.add(new Rabbit());  // Throws an exception: ClassCastException ... cannot be cast to Comparable

        // Just like searching and sorting, you can tell collections that require sorting that you wish to use a
        // specific Comparator:
        Set<Rabbit> rabbit = new TreeSet<>(new Comparator<Rabbit>() {
            @Override
            public int compare(Rabbit r1, Rabbit r2) {
                return r1.id - r2.id;
            }
        });
        rabbit.add(new Rabbit());
    }
}
