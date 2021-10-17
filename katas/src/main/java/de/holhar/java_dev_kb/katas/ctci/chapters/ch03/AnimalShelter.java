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

        private final Animal animal;
        private Node<T> next;

        public Node(Animal animal) {
            this.animal = animal;
        }

        public Animal getAnimal() {
            return animal;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }

    private Node<T> first;
    private Node<T> last;
    private int size;

    public int size() {
        return size;
    }

    public void enqueue(Animal animal) {
        if (first == null) {
            first = new Node<>(animal);
            last = first;
        } else {
            last.next = new Node<>(animal);
            last = last.next;
        }
        size++;
    }

    public Cat dequeueCat() {
        Node<T> precursor = first;
        Node<T> iterator = first;
        while (iterator.next != null) {
            if (iterator.getAnimal() instanceof Cat) {
                Cat result = (Cat)iterator.getAnimal();
                if (iterator.getNext() != null) {
                    if (iterator.equals(first)) {
                        first = iterator.getNext();
                    } else {
                        precursor.setNext(iterator.getNext());
                    }
                    size--;
                    return result;
                } else {
                    first = iterator.getNext();
                    size--;
                    return result;
                }
            } else {
                precursor = iterator;
                iterator = iterator.getNext();
            }
        }
        if (iterator.getAnimal() instanceof Cat) {
            Cat result = (Cat)iterator.getAnimal();
            if (iterator.getNext() != null) {
                if (iterator.equals(first)) {
                    first = iterator.getNext();
                } else {
                    precursor.setNext(iterator.getNext());
                }
                size--;
                return result;
            } else {
                first = precursor;
                precursor.setNext(null);
                size--;
                return result;
            }
        } else {
            return null;
        }
    }

    public Dog dequeueDog() {
        Node<T> precursor = first;
        Node<T> iterator = first;
        while (iterator.next != null) {
            if (iterator.getAnimal() instanceof Dog) {
                Dog result = (Dog)iterator.getAnimal();
                if (iterator.getNext() != null) {
                    if (iterator.equals(first)) {
                        first = iterator.getNext();
                    } else {
                        precursor.setNext(iterator.getNext());
                    }
                    size--;
                    return result;
                } else {
                    first = iterator.getNext();
                    size--;
                    return result;
                }
            } else {
                precursor = iterator;
                iterator = iterator.getNext();
            }
        }
        if (iterator.getAnimal() instanceof Dog) {
            Dog result = (Dog)iterator.getAnimal();
            if (iterator.getNext() != null) {
                if (iterator.equals(first)) {
                    first = iterator.getNext();
                } else {
                    precursor.setNext(iterator.getNext());
                }
                size--;
                return result;
            } else {
                first = precursor;
                precursor.setNext(null);
                size--;
                return result;
            }
        } else {
            return null;
        }
    }

    public Animal dequeueAny() {
        if (first.getNext() != null) {
            Node<T> next = first.getNext();
            Animal result = first.getAnimal();
            first = next;
            size--;
            return result;
        } else {
            Animal result = first.getAnimal();
            first = null;
            last = null;
            size--;
            return result;
        }
    }
}
