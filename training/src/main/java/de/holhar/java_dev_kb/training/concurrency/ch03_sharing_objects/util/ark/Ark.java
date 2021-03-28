package de.holhar.java_dev_kb.training.concurrency.ch03_sharing_objects.util.ark;

import java.util.Set;
import java.util.TreeSet;

public class Ark {

    private final Set<AnimalPair> animalPairs = new TreeSet<>();

    public void load(AnimalPair pair) {
        animalPairs.add(pair);
    }
}
