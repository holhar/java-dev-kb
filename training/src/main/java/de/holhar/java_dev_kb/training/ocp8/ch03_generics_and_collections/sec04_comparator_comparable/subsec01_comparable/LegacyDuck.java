package de.holhar.java_dev_kb.training.ocp8.ch03_generics_and_collections.sec04_comparator_comparable.subsec01_comparable;

public class LegacyDuck implements Comparable {

    private String name;

    @Override
    public int compareTo(Object o) {
        // Cast because no generics
        LegacyDuck d = (LegacyDuck) o;
        return name.compareTo(d.name);
    }
}
