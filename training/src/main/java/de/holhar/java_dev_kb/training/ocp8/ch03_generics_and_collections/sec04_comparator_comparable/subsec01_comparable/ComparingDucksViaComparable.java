package de.holhar.java_dev_kb.training.ocp8.ch03_generics_and_collections.sec04_comparator_comparable.subsec01_comparable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class ComparingDucksViaComparable {

    public static void main(String[] args) {

        List<Duck> ducks = new ArrayList<>();
        ducks.add(new Duck("Quack"));
        ducks.add(new Duck("Puddles"));
        Collections.sort(ducks);
        println(ducks);

        Animal a1 = new Animal(5);
        Animal a2 = new Animal(7);
        println(a1.compareTo(a2));
        println(a1.compareTo(a1));
        println(a2.compareTo(a1));
    }
}
