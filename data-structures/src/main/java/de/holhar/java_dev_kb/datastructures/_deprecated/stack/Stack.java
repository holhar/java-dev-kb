package de.holhar.java_dev_kb.datastructures._deprecated.stack;

// Implementing a Stack
public class Stack<E> {

    public StackNode<E> top;
    public int size;

    public Stack() {
    }

    public Stack(E element) {
        top = new StackNode<E>(element);
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
        StackNode<E> n = new StackNode<E>(data);
        n.next = top;
        top = n;
        size++;
    }

    public E peek() {
        return top.element;
    }
}

class StackNode<E> {

    public E element;
    public StackNode<E> next;

    public StackNode(E element) {
        this.element = element;
    }
}
