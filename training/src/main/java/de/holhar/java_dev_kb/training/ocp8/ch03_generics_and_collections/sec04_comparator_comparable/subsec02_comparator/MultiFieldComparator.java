package de.holhar.java_dev_kb.training.ocp8.ch03_generics_and_collections.sec04_comparator_comparable.subsec02_comparator;

import java.util.Comparator;

public class MultiFieldComparator implements Comparator<Squirrel> {

    /*
     * This works, but it's not easy to read and reasy to get wrong
    public int compare(Squirrel s1, Squirrel s2) {
        int result = s1.getSpecies().compareTo(s2.getSpecies());
        if (result != 0) {
            return result;
        }
        return s1.getWeight() - s2.getWeight();
    }
    */

    /*
     * Comparing multiple fields the new cool Java 8 style
     */
    public int compare(Squirrel s1, Squirrel s2) {
        Comparator<Squirrel> c = Comparator.comparing(Squirrel::getSpecies);
        c.thenComparing(Squirrel::getWeight);
        return c.compare(s1, s2);
    }
}
