package de.holhar.java_dev_kb.training.concurrency.ch03_sharing_objects.s4_immutability;

import de.holhar.java_dev_kb.training.concurrency.utils.Immutable;

import java.util.HashSet;
import java.util.Set;

/**
 * Immutable class built out of mutable underlying objects.
 */
@Immutable
public class ThreeStooges {

    private final Set<String> stooges = new HashSet<>();

    public ThreeStooges() {
        stooges.add("Moe");
        stooges.add("Larry");
        stooges.add("Curly");
    }

    public boolean isStooge(String name) {
        return stooges.contains(name);
    }
}
