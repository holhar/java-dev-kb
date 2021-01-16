package de.holhar.java_dev_kb.training.ocp8.ch02_design_patterns_and_principles.sec05_design_patterns.subsec04_factory;

import de.holhar.java_dev_kb.training.ocp8.ch02_design_patterns_and_principles.sec05_design_patterns.subsec03_builder.AnimalBuilder;

import java.util.Arrays;

public class Zookeeper {

    public static void main(String[] args) {
        AnimalBuilder builder = new AnimalBuilder();
        builder.setAge(10).setFavoriteFoods(Arrays.asList("fish","robben")).setSpecies("polar bear");
        // We don't care about the particular type of food
        final Food food = FoodFactory.getFood(builder.build());
        food.consumed();
    }
}
