package de.holhar.java_dev_kb.katas.ctci.chapters.ch03;

// Implementing a Stack
public class CustomStack<E> {

    private StackNode<E> top;
    private int size;

    public CustomStack() {
    }

    public CustomStack(E element) {
        top = new StackNode<>(element);
        size = 1;
    }

    public E pop() {
        if (top != null) {
            E element = top.element;
            top = top.next;
            size--;
            return element;
        }
        return null;
    }

    public void push(E data) {
        StackNode<E> n = new StackNode<>(data);
        n.next = top;
        top = n;
        size++;
    }

    public int size() {
        return size;
    }

    public E peek() {
        return top.element;
    }

    static class StackNode<E> {

        private final E element;
        private StackNode<E> next;

        public StackNode(E element) {
            this.element = element;
        }
    }
}