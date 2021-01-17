/*
 * Comments can be added even before the package declaration.
 */
package de.holhar.java_dev_kb.training.oca8.ch01_java_building_blocks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Not using 'public' keyword here as then file name and class name must match
class Animal {

    private static final Logger LOGGER = LoggerFactory.getLogger(Animal.class);

    String name;

    // Different possibilities to provide the 'args' parameter:
    // public static void main(String[] args) {
    // public static void main(String args[]) {
    public static void main(String... args) {
        Animal animal = new Animal();
        animal.setName(args[0]);
        LOGGER.debug("The animals name is {}", animal.getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }
}

// Defining a second class in the same file
class Animal2 {

}
