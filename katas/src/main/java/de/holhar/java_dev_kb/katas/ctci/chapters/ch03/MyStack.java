package de.holhar.java_dev_kb.katas.ctci.chapters.ch03;

import java.util.Objects;

public class MyStack<E extends Comparable<E>> implements Comparable<E> {

    /*
     * Basic stack implementation.
     */
    public static class Node<E> {
        private final E data;
        private Node<E> next;

        public Node(E data) {
            this.data = data;
        }

        public E getData() {
            return data;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }

    private Node<E> top;
    private Node<E> min; // added with exercise 3.2
    private int size;

    public void push(E data) {
        if (top == null) {
            top = new Node<>(data);
        } else {
            Node<E> next = top;
            top = new Node<>(data);
            top.setNext(next);
        }
        setMin(data);
        size++;
    }

    public E peek() {
        if (top == null) {
            throw new IllegalStateException("stack is empty");
        }
        return top.getData();
    }

    public E pop() {
        if (top == null) {
            throw new IllegalStateException("stack is empty");
        }
        E topData = top.data;
        Node<E> next = top.getNext();
        top.setNext(null);
        top = next;
        size--;
        if (size == 0) {
            min = null;
        } else if (topData.equals(min.getData())) {
            resetMin();
        }
        return topData;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }

    /*
     * 3.2 Stack Min: How would you design a stack which, in addition to push and pop, has a function min which
     * returns the minimum element? Push, pop and min should all operate in O(1) time.
     */
    public E min() {
        if (min == null) {
            return null;
        }
        return min.getData();
    }

    private void setMin(E data) {
        if (min == null) {
            min = new Node<>(data);
        }
        if (min.getData().compareTo(data) > 0) {
            min = new Node<>(data);
        }
    }

    private void resetMin() {
        Node<E> reference = top;
        Node<E> iterator = top;
        while (iterator.getNext() != null) {
            if (reference.getData().compareTo(iterator.getData()) > 0) {
                reference = iterator;
            }
            iterator = iterator.getNext();
        }
        min = reference;
    }

    /*
     * 3.5 Sort Stack: Write a program to sort a stack such that the smallest items are on the top. You can use
     * an additional temporary stack, but you may not copy the elements into any other data structure
     * (such as an array). The stack supports the following operations: push, pop, peek, and isEmpty.
     */
    public void sort() {
        MyStack<E> reverseSortedStack = new MyStack<>();
        MyStack<E> bufferStack = new MyStack<>();

        while (!isEmpty()) {
            if (top.getData().equals(min.getData())) {
                reverseSortedStack.push(pop());
                while (!bufferStack.isEmpty()) {
                    push(bufferStack.pop());
                }
            } else {
                bufferStack.push(pop());
            }
        }

        while (!reverseSortedStack.isEmpty()) {
            push(reverseSortedStack.pop());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyStack<?> myStack = (MyStack<?>) o;
        return Objects.equals(top, myStack.top) && Objects.equals(min, myStack.min);
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public int compareTo(E other) {
        return min.getData().compareTo(other);
    }
}
