package de.holhar.java_dev_kb.training.ocp8.ch03_generics_and_collections.sec02_generics.subsec07_bounds;

import java.util.ArrayList;
import java.util.List;

public class UpperBoundedWildcards {

    /*
     * Something interesting happens when we work with upper bounds or unbounded wildcards. The list becomes logically
     * immutable. Immutable means that the object cannot be modified. The problems stems from the fact that Java doesn't
     * know what type <? extends Bird> really is. It could be List<Bird> or List<Sparrow> or some other generic type that
     * hasn't even been written yet.
     */
    public static void main(String[] args) {
        List<? extends Bird> birds = new ArrayList<>();
        // birds.add(new Sparrow());   // DOES NOT COMPILE
        // birds.add(new Bird());      // DOES NOT COMPILE
    }

    static class Sparrow extends Bird {
    }

    static class Bird {
    }
}
