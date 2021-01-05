package de.holhar.java_dev_kb.katas.ctci.chapters.ch03;

// Implementing a Queue
public class CustomQueue<E> {

    public QueueNode<E> first, last;

    public CustomQueue() {
    }

    public CustomQueue(E element) {
        QueueNode<E> last = new QueueNode<E>(element);
        first = last;
    }

    public void enqueue(E element) {
        QueueNode<E> n = new QueueNode<E>(element);
        if (first == null) {
            last = n;
            first = last;
        } else {
            last.next = n;
            last = last.next;
        }
    }

    public E dequeue() {
        E element;
        if (first != null) {
            element = (E) first.element;
            first = first.next;
            return element;
        }
        return null;
    }
}

class QueueNode<E> {

    public E element;
    public QueueNode<E> next;

    public QueueNode(E element) {
        this.element = element;
    }
}