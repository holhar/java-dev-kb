package de.holhar.java_dev_kb.training.ocp8.ch02_design_patterns_and_principles.sec05_design_patterns.subsec02_immutable_objects;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hhs@dasburo.com
 */
public final class Animal {

    private final String species;
    private final int age;
    private final List<String> favoriteFoods;

    public Animal(String species, int age, List<String> favoriteFoods) {
        this.species = species;
        this.age = age;
        if (favoriteFoods == null) {
            throw new IllegalArgumentException("favoriteFoods is required");
        }
        this.favoriteFoods = new ArrayList<>(favoriteFoods);
    }

    public String getSpecies() {
        return species;
    }

    public int getAge() {
        return age;
    }

    public int getFavoriteFoodsCount() {
        return favoriteFoods.size();
    }

    public String getFavoriteFoot(int index) {
        return favoriteFoods.get(index);
    }

    // MAKES CLASS MUTABLE!
    //public List<String> getFavoriteFoods() {
    //    return favoriteFoods;
    //}
}
