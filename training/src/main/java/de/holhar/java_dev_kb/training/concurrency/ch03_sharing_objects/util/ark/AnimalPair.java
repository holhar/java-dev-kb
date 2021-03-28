package de.holhar.java_dev_kb.training.concurrency.ch03_sharing_objects.util.ark;

public class AnimalPair {

    private final Animal a1;
    private final Animal a2;

    public AnimalPair(Animal a1, Animal a2) {
        this.a1 = a1;
        this.a2 = a2;
    }

    public Animal getA1() {
        return a1;
    }

    public Animal getA2() {
        return a2;
    }
}
