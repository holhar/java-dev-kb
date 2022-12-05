package de.holhar.java_dev_kb.data_structures_and_algorithms.ch01_fundamental_data_structures.s3_circularly_linked_lists;

public class CircularlyLinkedList<E> {

  private static class Node<E> {
    private final E element;
    private Node<E> next;

    public Node(E element, Node<E> next) {
      this.element = element;
      this.next = next;
    }

    public E getElement() {
      return element;
    }

    public Node<E> getNext() {
      return next;
    }

    public void setNext(
        Node<E> next) {
      this.next = next;
    }
  }

  private Node<E> tail;
  private int size;

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public E first() {
    if (isEmpty())
      return null;

    return tail.getNext().getElement();
  }

  public E last() {
    if (isEmpty())
      return null;

    return tail.getElement();
  }
}
