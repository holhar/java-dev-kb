package de.holhar.java_dev_kb.datastructures._deprecated.list;

// Implementing a singly linked list
public class LinkedList<E> {

    public LinkedListNode<E> first;

    public LinkedList() {
    }

    public LinkedList(E element) {
        first = new LinkedListNode<E>(element);
    }

    public void add(E element) {
        if (first == null) {
            first = new LinkedListNode<E>(element);
        } else {
            first.appendToTail(element);
        }
    }

    public E get(int index) {

        if (index == 0) return first.element;

        int counter = 0;
        LinkedListNode<E> n = first;

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

        LinkedListNode<E> iterator = first;

        while (iterator != null) {
            LinkedListNode<E> innerIterator = iterator;
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
        LinkedListNode<E> it = first;

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
        LinkedListNode<E> n = first;

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

    // Question 4
//	public void partition(int pivot) {
//		LinkedList<E> smallerThanList = new LinkedList<E>();
//		LinkedList<E> greaterThanList = new LinkedList<E>();
//
//		if((int)first.element < pivot) smallerThanList.add(first.element);
//		else greaterThanList.add(first.element);
//
//		LinkedListNode<E> it = first;
//
//		while(it.next != null) {
//			if((int)it.next.element < pivot) smallerThanList.add(it.next.element);
//			else greaterThanList.add(it.next.element);
//			it = it.next;
//		}
//
//		first = smallerThanList.first;
//		LinkedListNode<E> n = first;
//
//		while(n.next != null) {
//			n = n.next;
//		}
//		n.next = greaterThanList.first;
//	}
}

class LinkedListNode<E> {

    public LinkedListNode<E> next = null;
    public E element;

    public LinkedListNode(E element) {
        this.element = element;
    }

    public void appendToTail(E element) {
        LinkedListNode<E> end = new LinkedListNode<E>(element);
        LinkedListNode<E> n = this;
        while (n.next != null) {
            n = n.next;
        }
        n.next = end;
    }
}
