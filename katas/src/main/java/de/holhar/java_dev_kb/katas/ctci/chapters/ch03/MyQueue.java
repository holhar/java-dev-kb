package de.holhar.java_dev_kb.katas.ctci.chapters.ch03;

public class MyQueue<E> {

    /*
     * Basic queue implementation
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

    private Node<E> first;
    private Node<E> last;

    public void add(E data) {
        if (first == null) {
            first = new Node<>(data);
            last = first;
        } else {
            Node<E> runner = first;
            while (runner.getNext() != null) {
                runner = runner.getNext();
            }
            runner.setNext(new Node<>(data));
            last = runner.getNext();
        }
    }

    public E peek() {
        if (first == null) {
            throw new IllegalStateException("queue is empty");
        }
        return first.getData();
    }

    public E getLast() {
        return last.getData();
    }

    public E remove() {
        if (first == null) {
            throw new IllegalStateException("queue is empty");
        }
        E data = first.getData();
        first = first.getNext();
        if (first == null) {
            last = null;
        }
        return data;
    }

    public boolean isEmpty() {
        return first == null;
    }
}
