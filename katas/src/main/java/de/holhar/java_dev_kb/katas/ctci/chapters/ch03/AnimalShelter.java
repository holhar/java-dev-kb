package de.holhar.java_dev_kb.katas.ctci.chapters.ch03;

import de.holhar.java_dev_kb.katas.ctci.chapters.ch03.animals.Animal;
import de.holhar.java_dev_kb.katas.ctci.chapters.ch03.animals.Cat;
import de.holhar.java_dev_kb.katas.ctci.chapters.ch03.animals.Dog;

/**
 * 3.6 Animal Shelter: An animal shelter, which holds only dogs and cats, operates on a strictly "first in, first
 * out" basis. People must adopt either the "oldest" (based on arrival time) of all animals at the shelter,
 * or they can select whether they would prefer a dog or a cat (and will receive the oldest animal of
 * that type). They cannot select which specific animal they would like. Create the data structures to
 * maintain this system and implement operations such as enqueue, dequeueAny, dequeueDog,
 * and dequeueCat.You may use the built-in LinkedList data structure.
 */
public class AnimalShelter<T extends Animal> {

    public static class Node<T extends Animal> {
    }

    public int size() {
        return -1;
    }

    public void enqueue(Animal animal) {
    }

    public Cat dequeueCat() {
        return null;
    }

    public Dog dequeueDog() {
        return null;
    }

    public Animal dequeueAny() {
        return null;
    }
}
