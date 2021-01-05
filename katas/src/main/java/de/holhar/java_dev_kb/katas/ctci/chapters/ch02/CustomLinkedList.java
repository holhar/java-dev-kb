package de.holhar.java_dev_kb.katas.ctci.chapters.ch02;

/**
 * Implementation of a singly linked list
 */
public class CustomLinkedList<E> {

    private Node<E> first;

    public CustomLinkedList() {
    }

    public CustomLinkedList(E element) {
        first = new Node<>(element);
    }

    public void add(E element) {
        if (first == null) {
            first = new Node<>(element);
        } else {
            first.appendToTail(element);
        }
    }

    public E get(int index) {

        if (index == 0) return first.element;

        int counter = 0;
        Node<E> n = first;

        while (n.next != null) {
            counter++;
            n = n.next;
            if (counter == index) {
                return n.element;
            }
        }
        return null;
    }

    // Question 1
    public void removeDuplicates() {
        if (first == null) return;

        Node<E> iterator = first;

        while (iterator != null) {
            Node<E> innerIterator = iterator;
            while (innerIterator.next != null) {
                if (innerIterator.next.element == iterator.element) {
                    innerIterator.next = innerIterator.next.next;
                } else {
                    innerIterator = innerIterator.next;
                }
            }
            iterator = iterator.next;
        }
    }

    // Question 2
    public E getKthToLastElement(int k) {

        int size = 1;
        int counter = 0;
        int index;
        Node<E> it = first;

        while (it.next != null) {
            it = it.next;
            size++;
        }

        index = size - k;
        if (index == 0) return first.element;
        it = first;

        while (it.next != null) {
            counter++;
            if (counter == index) {
                return it.next.element;
            }
            it = it.next;
        }
        return null;
    }

    // Question 3
    public boolean deleteNode(E element) {
        Node<E> n = first;

        if (n.element.equals(element)) {
            first = first.next;
            return true; // moved first
        }

        while (n.next != null) {
            if (n.next.element.equals(element)) {
                n.next = n.next.next;
                return true; // head didn't change
            }
            n = n.next;
        }
        return false; // node not found
    }

    // TODO Question 4
//	public void partition(int pivot) {
//		CustomLinkedList<E> smallerThanList = new CustomLinkedList<E>();
//		CustomLinkedList<E> greaterThanList = new CustomLinkedList<E>();
//
//		if((int)first.element < pivot) smallerThanList.add(first.element);
//		else greaterThanList.add(first.element);
//
//		Node<E> it = first;
//
//		while(it.next != null) {
//			if((int)it.next.element < pivot) smallerThanList.add(it.next.element);
//			else greaterThanList.add(it.next.element);
//			it = it.next;
//		}
//
//		first = smallerThanList.first;
//		Node<E> n = first;
//
//		while(n.next != null) {
//			n = n.next;
//		}
//		n.next = greaterThanList.first;
//	}

    static class Node<E> {

        private Node<E> next = null;
        private final E element;

        public Node(E element) {
            this.element = element;
        }

        public void appendToTail(E element) {
            Node<E> end = new Node<>(element);
            Node<E> n = this;
            while (n.next != null) {
                n = n.next;
            }
            n.next = end;
        }
    }
}
