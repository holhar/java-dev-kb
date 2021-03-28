package de.holhar.java_dev_kb.training.concurrency.ch03_sharing_objects.s3_thread_confinement;

import de.holhar.java_dev_kb.training.concurrency.ch03_sharing_objects.util.ark.Animal;
import de.holhar.java_dev_kb.training.concurrency.ch03_sharing_objects.util.ark.AnimalPair;
import de.holhar.java_dev_kb.training.concurrency.ch03_sharing_objects.util.ark.Ark;
import de.holhar.java_dev_kb.training.concurrency.ch03_sharing_objects.util.ark.SpeciesGenderComparator;

import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Thread confinement of local primitive and reference variables.
 */
public class MosesStory {

    private final Ark ark;

    public MosesStory(Ark ark) {
        this.ark = ark;
    }

    public int loadTheArk(Collection<Animal> candidates) {
        SortedSet<Animal> animals;
        int numPairs = 0;
        Animal candidate = null;

        // animals confined to method, don't let them escape!
        animals = new TreeSet<>(new SpeciesGenderComparator());
        animals.addAll(candidates);
        for (Animal a : animals) {
            if (candidate == null || !candidate.isPotentialMate(a)) {
                candidate = a;
            } else {
                ark.load(new AnimalPair(candidate, a));
                ++numPairs;
                candidate = null;
            }
        }
        return numPairs;
    }
}
