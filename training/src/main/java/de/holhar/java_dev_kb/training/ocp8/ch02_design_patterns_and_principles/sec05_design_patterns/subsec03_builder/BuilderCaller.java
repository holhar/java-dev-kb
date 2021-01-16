package de.holhar.java_dev_kb.training.ocp8.ch02_design_patterns_and_principles.sec05_design_patterns.subsec03_builder;

import de.holhar.java_dev_kb.training.ocp8.ch02_design_patterns_and_principles.sec05_design_patterns.subsec02_immutable_objects.Animal;

import java.util.Arrays;

public class BuilderCaller {

    public static void main(String[] args) {
        AnimalBuilder builder = new AnimalBuilder();
        // Methods are chainable as every setter returns the builder instance in use
        builder.setAge(4).setFavoriteFoods(Arrays.asList("grass", "fish")).setSpecies("duck");
        Animal duck = builder.build();

        // It's not needed to save the builder instance
        Animal flamingo = new AnimalBuilder()
                                .setFavoriteFoods(Arrays.asList("algae","insects"))
                                // Not necessary to set every property of the instance to be created
                                // .setAge(5)
                                .setSpecies("flamingo").build();
    }
}
