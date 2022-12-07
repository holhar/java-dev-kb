package de.holhar.java_dev_kb.data_structures_and_algorithms.ch05_list_and_iterator_ADTs.s3_positional_lists;

public class LinkedPositionalList<E> implements PositionalList<E> {

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

  public LinkedPositionalList() {
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
