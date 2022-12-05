package de.holhar.java_dev_kb.data_structures_and_algorithms.ch01_fundamental_data_structures.s4_doubly_linked_lists;

public class DoublyLinkedList<E> {

  private static class Node<E> {

    private final E element;
    private Node<E> next;
    private Node<E> prev;

    public Node(E element, Node<E> next, Node<E> prev) {
      this.element = element;
      this.next = next;
      this.prev = prev;
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

    public Node<E> getPrev() {
      return prev;
    }

    public void setPrev(
        Node<E> prev) {
      this.prev = prev;
    }
  }

  private Node<E> header;
  private Node<E> trailer;
  private int size;

  public DoublyLinkedList() {
    header = new Node<>(null, null, null);
    trailer = new Node<>(null, header, null);
    header.setNext(trailer);
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public E first() {
    if (isEmpty())
      return null;
    // first element is beyond header
    return header.getNext().getElement();
  }

  public E last() {
    if (isEmpty())
      return null;
    // last element is before trailer
    return trailer.getPrev().getElement();
  }

  public void addFirst(E e) {
    addBetween(e, header, header.getNext());
  }

  public void addLast(E e) {
    addBetween(e, trailer.getPrev(), trailer);
  }

  public E removeFirst() {
    if (isEmpty())
      return null;
    return remove(header.getNext());
  }

  public E removeLast() {
    if (isEmpty())
      return null;
    return remove(trailer.getPrev());
  }

  private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
    Node<E> newest = new Node<>(e, successor, predecessor);
    predecessor.setNext(newest);
    successor.setPrev(newest);
    size++;
  }

  private E remove(Node<E> node) {
    Node<E> predecessor = node.getPrev();
    Node<E> successor = node.getNext();
    predecessor.setNext(successor);
    successor.setPrev(predecessor);
    size--;
    return node.getElement();
  }
}
