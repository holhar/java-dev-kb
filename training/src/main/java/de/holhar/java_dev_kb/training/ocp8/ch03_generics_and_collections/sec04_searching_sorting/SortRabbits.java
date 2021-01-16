package de.holhar.java_dev_kb.training.ocp8.ch03_generics_and_collections.sec04_searching_sorting;

import java.util.*;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class SortRabbits {

    static class Rabbit {
        int id;
    }

    public static void main(String[] args) {
        List<Rabbit> rabbits = new ArrayList<>();
        rabbits.add(new Rabbit());

        // DOES NOT COMPILE - rabbits does not implement Comparable
        // Collections.sort(rabbits);

        // Passing in a Comparator it works
        Comparator<Rabbit> c1 = (r1, r2) -> r1.id - r2.id;
        Collections.sort(rabbits, c1);

        // The same is possible for binarySearch (though in the following example the result will be undefined as we're
        // using a Comparator that reverses the order - binarySearch expects a list in ascending order).)
        List<String> names = Arrays.asList("Fluffy", "Hoppy");
        Comparator<String> c2 = Comparator.reverseOrder();
        int index = Collections.binarySearch(names, "Hoppy", c2);
        println(index);
    }
}
