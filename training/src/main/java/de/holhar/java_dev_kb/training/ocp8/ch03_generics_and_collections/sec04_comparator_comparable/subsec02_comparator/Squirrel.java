package de.holhar.java_dev_kb.training.ocp8.ch03_generics_and_collections.sec04_comparator_comparable.subsec02_comparator;

/**
 * @author hhs@dasburo.com
 */
public class Squirrel {

    private int weight;
    private String species;

    public Squirrel(String theSpecies) {
        if (theSpecies == null) {
            throw new IllegalArgumentException();
        }
        species = theSpecies;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getSpecies() {
        return species;
    }
}
