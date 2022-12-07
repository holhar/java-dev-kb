package de.holhar.java_dev_kb.data_structures_and_algorithms.ch05_list_and_iterator_ADTs.s4_iterators;

import de.holhar.java_dev_kb.data_structures_and_algorithms.ch05_list_and_iterator_ADTs.s3_positional_lists.Position;
import de.holhar.java_dev_kb.data_structures_and_algorithms.ch05_list_and_iterator_ADTs.s3_positional_lists.PositionalList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IterablePositionalList<E> implements PositionalList<E> {

  // ---------------------- start of nested PositionIterator class ------------------------
  private class PositionIterator implements Iterator<Position<E>> {

    private Position<E> cursor = first();
    private Position<E> recent = null;

    @Override
    public boolean hasNext() {
      return cursor != null;
    }

    @Override
    public Position<E> next() {
      if (cursor == null)
        throw new NoSuchElementException("nothing left");
      recent = cursor;
      cursor = after(cursor);
      return recent;
    }

    @Override
    public void remove() {
      if (recent == null)
        throw new IllegalStateException("nothing to remove");
      IterablePositionalList.this.remove(recent);
      recent = null; // do not allow remove again until next is called
    }
  }
  // ---------------------- end of nested ArrayIterator class ------------------------

  // ---------------------- start of nested PositionIterable class ------------------------
  private class PositionIterable implements Iterable<Position<E>> {
    public Iterator<Position<E>> iterator() {
      return new PositionIterator();
    }
  }
  // ---------------------- end of nested PositionIterable class ------------------------

  /**
   * Returns an iterable representation of the list's poisitions.
   */
  public Iterable<Position<E>> positions() {
    return new PositionIterable();
  }

  // ---------------------- start of nested ElementIterator class ------------------------
  /**
   * This class adapts the iteration produced by positions() to return elements.
   */
  private class ElementIterator implements Iterator<E> {

    Iterator<Position<E>> posIterator = new PositionIterator();

    @Override
    public boolean hasNext() {
      return posIterator.hasNext();
    }

    @Override
    public E next() {
      return posIterator.next().getElement();
    }

    @Override
    public void remove() {
      posIterator.remove();
    }
  }
  // ---------------------- end of nested ElementIterator class ------------------------

  /**
   * Returns an iterator of the elements stored in the list.
   */
  public Iterator<E> iterator() {
    return new ElementIterator();
  }

  private static class Node<E> implements Position<E> {

    private E element;
    private Node<E> prev;
    private Node<E> next;

    public Node(E element, Node<E> prev, Node<E> next) {
      this.element = element;
      this.prev = prev;
      this.next = next;
    }

    @Override
    public E getElement() throws IllegalStateException {
      // convention for defunct code
      if (next == null)
        throw new IllegalStateException("Position no longer valid");
      return element;
    }

    public void setElement(E element) {
      this.element = element;
    }

    public Node<E> getPrev() {
      return prev;
    }

    public void setPrev(
        Node<E> prev) {
      this.prev = prev;
    }

    public Node<E> getNext() {
      return next;
    }

    public void setNext(
        Node<E> next) {
      this.next = next;
    }
  }

  private Node<E> header;
  private Node<E> trailer;
  private int size;

  public IterablePositionalList() {
    header = new Node<>(null, null, null);
    trailer = new Node<>(null, header, null);
    header.setNext(trailer);
  }

  /*
   * Validates the Position and returns it as a Node.
   */
  private Node<E> validate(Position<E> p) throws IllegalArgumentException {
    if (!(p instanceof Node))
      throw new IllegalArgumentException("Invalid p");

    Node<E> node = (Node<E>) p;

    // convention for defunct code
    if (node.getNext() == null)
      throw new IllegalArgumentException("p is no longer in the list");

    return node;
  }

  /*
   * Returns the given Node as a Position (or null, if it is a sentinel).
   */
  private Position<E> position(Node<E> node) {
    if (node == header || node == trailer)
      return null;
    return node;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public Position<E> first() {
    return position(header.getNext());
  }

  @Override
  public Position<E> last() {
    return position(trailer.getPrev());
  }

  @Override
  public Position<E> before(Position<E> p) throws IllegalArgumentException {
    Node<E> node = validate(p);
    return position(node.getPrev());
  }

  @Override
  public Position<E> after(Position<E> p) throws IllegalArgumentException {
    Node<E> node = validate(p);
    return position(node.getNext());
  }

  private Position<E> addBetween(E e, Node<E> pred, Node<E> succ) {
    Node<E> newest = new Node<>(e, pred, succ);
    pred.setNext(newest);
    succ.setPrev(newest);
    size++;
    return newest;
  }

  @Override
  public Position<E> addFirst(E e) {
    return addBetween(e, header, header.getNext());
  }

  @Override
  public Position<E> addLast(E e) {
    return addBetween(e, trailer.getPrev(), trailer);
  }

  @Override
  public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
    Node<E> node = validate(p);
    return addBetween(e, node.getPrev(), node);
  }

  @Override
  public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
    Node<E> node = validate(p);
    return addBetween(e, node, node.getNext());
  }

  /*
   * Replaces the element stored at Position p and returns the replaced element.
   */
  @Override
  public E set(Position<E> p, E e) throws IllegalArgumentException {
    Node<E> node = validate(p);
    E answer = node.getElement();
    node.setElement(e);
    return answer;
  }

  @Override
  public E remove(Position<E> p) throws IllegalArgumentException {
    Node<E> node = validate(p);
    Node<E> predecessor = node.getPrev();
    Node<E> successor = node.getNext();
    predecessor.setNext(successor);
    successor.setPrev(predecessor);
    E answer = node.getElement();
    node.setElement(null);
    node.setPrev(null);
    node.setNext(null);
    size--;
    return answer;
  }
}
