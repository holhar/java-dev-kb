package de.holhar.java_dev_kb.training.ocp8.ch02_design_patterns_and_principles.sec05_design_patterns.subsec03_builder;

import de.holhar.java_dev_kb.training.ocp8.ch02_design_patterns_and_principles.sec05_design_patterns.subsec02_immutable_objects.Animal;

import java.util.List;

/**
 * @author hhs@dasburo.com
 *
 * The builder is mutable.
 *
 * Benefit: Over time this pattern leads to more maintainable code, e.g. if a new optional field is added to the Animal
 * class , then our code that creates the object using the AnimalBuilder class will not need to be changed.
 */
public class AnimalBuilder {
    private String species;
    private int age;
    private List<String> favoriteFoods;

    public AnimalBuilder setSpecies(String species) {
        this.species = species;
        return this;
    }

    public AnimalBuilder setAge(int age) {
        this.age = age;
        return this;
    }

    public AnimalBuilder setFavoriteFoods(List<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
        return this;
    }

    /**
     * @return instance of immutable Animal class
     */
    public Animal build() {
        return new Animal(species, age, favoriteFoods);
    }
}
