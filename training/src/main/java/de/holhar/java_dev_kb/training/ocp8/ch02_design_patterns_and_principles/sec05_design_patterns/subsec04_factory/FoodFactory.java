package de.holhar.java_dev_kb.training.ocp8.ch02_design_patterns_and_principles.sec05_design_patterns.subsec04_factory;

import de.holhar.java_dev_kb.training.ocp8.ch02_design_patterns_and_principles.sec05_design_patterns.subsec02_immutable_objects.Animal;

/**
 *
 * Ensures loose coupling between Animal and Food classes
 */
public class FoodFactory {

    public static Food getFood(Animal animal) {
        switch (animal.getSpecies()) {
            case "zebra": return new Hay(100);
            case "rabbit": return new Pellets(5);
            case "goat": return new Pellets(30);
            case "polar bear": return new Fish(10);
        }

        // Good practice to throw an exception if no matching subclass could be found
        throw new UnsupportedOperationException("Unsupported animal: " + animal.getSpecies());
    }
}
