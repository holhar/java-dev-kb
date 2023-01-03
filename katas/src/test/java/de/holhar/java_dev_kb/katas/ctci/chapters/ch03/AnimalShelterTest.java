package de.holhar.java_dev_kb.katas.ctci.chapters.ch03;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import de.holhar.java_dev_kb.katas.ctci.chapters.ch03.animals.Animal;
import de.holhar.java_dev_kb.katas.ctci.chapters.ch03.animals.Cat;
import de.holhar.java_dev_kb.katas.ctci.chapters.ch03.animals.Dog;
import org.junit.jupiter.api.Test;

public class AnimalShelterTest {

    @Test
    void basicOperations() {
        AnimalShelter<Animal> animalShelter = new AnimalShelter<>();

        animalShelter.enqueue(new Dog("Alba"));
        animalShelter.enqueue(new Cat("Grinsekatze"));
        animalShelter.enqueue(new Dog("Scooby Doo"));
        animalShelter.enqueue(new Cat("Silvester"));
        animalShelter.enqueue(new Dog("Snoopy"));
        animalShelter.enqueue(new Cat("Garfield"));

        assertEquals(6, animalShelter.size());
        assertEquals("Alba", animalShelter.dequeueAny().getName());
        assertEquals(5, animalShelter.size());
        assertEquals("Scooby Doo", animalShelter.dequeueDog().getName());
        assertEquals(4, animalShelter.size());
        assertEquals("Grinsekatze", animalShelter.dequeueCat().getName());
        assertEquals(3, animalShelter.size());
        assertEquals("Silvester", animalShelter.dequeueCat().getName());
        assertEquals(2, animalShelter.size());
        assertEquals("Garfield", animalShelter.dequeueCat().getName());
        assertEquals(1, animalShelter.size());
        assertNull(animalShelter.dequeueCat());
        assertEquals("Snoopy", animalShelter.dequeueDog().getName());
        assertEquals(0, animalShelter.size());
    }

}
