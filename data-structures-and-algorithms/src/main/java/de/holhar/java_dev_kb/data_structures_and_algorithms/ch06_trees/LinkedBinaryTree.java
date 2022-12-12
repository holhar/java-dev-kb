package de.holhar.java_dev_kb.data_structures_and_algorithms.ch06_trees;

import java.util.Iterator;

/**
 * Concrete implementation of a binary tree using a node-based, linked structure.
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

  // ---------------------- nested Node class -----------------------------
  protected static class Node<E> implements Position<E> {
    private E element;
    private Node<E> parent;
    private Node<E> left;
    private Node<E> right;

    public Node(E element, Node<E> parent, Node<E> left, Node<E> right) {
      this.element = element;
      this.parent = parent;
      this.left = left;
      this.right = right;
    }

    @Override
    public E getElement() {
      return element;
    }

    public void setElement(E element) {
      this.element = element;
    }

    public Node<E> getParent() {
      return parent;
    }

    public void setParent(
        Node<E> parent) {
      this.parent = parent;
    }

    public Node<E> getLeft() {
      return left;
    }

    public void setLeft(
        Node<E> left) {
      this.left = left;
    }

    public Node<E> getRight() {
      return right;
    }

    public void setRight(
        Node<E> right) {
      this.right = right;
    }
  }
  // ------------------- end of nested Node class -------------------------

  /**
   * Factory method to create a new node storing element e.
   */
  protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
    return new Node<>(e, parent, left, right);
  }

  protected Node<E> root;
  private int size;

  protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
    if (!(p instanceof Node)) {
      throw new IllegalArgumentException("Not a valid position type");
    }
    Node<E> node = (Node<E>) p;
    // our convention of defunct node
    if (node.getParent() == node) {
      throw new IllegalArgumentException("p is no longer in the tree");
    }
    return node;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public Position<E> root() {
    return root;
  }

  @Override
  public Position<E> parent(Position<E> p) throws IllegalArgumentException {
    Node<E> node = validate(p);
    return node.getParent();
  }

  @Override
  public Position<E> left(Position<E> p) throws IllegalArgumentException {
    Node<E> node = validate(p);
    return node.getLeft();
  }

  @Override
  public Position<E> right(Position<E> p) throws IllegalArgumentException {
    Node<E> node = validate(p);
    return node.getRight();
  }

  // ------------- Update methods supported by this class --------------
  /**
   * Places element e at the root of an empty tree and returns its new Position.
   */
  public Position<E> addRoot(E e) throws IllegalStateException {
    if (!isEmpty())
      throw new IllegalStateException("Tree is not empty");
    root = createNode(e, null, null, null);
    size = 1;
    return root;
  }

  /**
   * Creates a new left child of Position p storing element e; returns its Position.
   */
  public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
    Node<E> parent = validate(p);
    if (parent.getLeft() != null)
      throw new IllegalArgumentException("p already has a left child");
    Node<E> leftChild = createNode(e, parent, null, null);
    parent.setLeft(leftChild);
    size++;
    return leftChild;
  }

  /**
   * Creates a new right child of Position p storing element e; returns its Position.
   */
  public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
    Node<E> parent = validate(p);
    if (parent.getRight() != null)
      throw new IllegalArgumentException("p already has a right child");
    Node<E> rightChild = createNode(e, parent, null, null);
    parent.setRight(rightChild);
    size++;
    return rightChild;
  }

  /**
   * Replaces the element at Position p with e and returns the replaced element.
   */
  public E set(Position<E> p, E e) throws IllegalArgumentException {
    Node<E> node = validate(p);
    E temp = node.getElement();
    node.setElement(e);
    return temp;
  }

  /**
   * Attaches trees t1 and t2 as left and right subtrees of external p.
   */
  public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {
    Node<E> node = validate(p);
    if (isInternal(p))
      throw new IllegalArgumentException("p must be a leaf");
    size += t1.size() + t2.size();
    if (!t1.isEmpty()) {
      t1.root.setParent(node);
      node.setLeft(t1.root);
      t1.root = null;
      t1.size = 0;
    }
    if (!t2.isEmpty()) {
      t2.root.setParent(node);
      node.setRight(t2.root);
      t2.root = null;
      t2.size = 0;
    }
  }

  /**
   * Removes the node at Position p and replaces it with its child, if any.
   */
  public E remove(Position<E> p) throws IllegalArgumentException {
    Node<E> node = validate(p);
    if (numChildren(p) == 2)
      throw new IllegalArgumentException("p has two children");
    Node<E> child = node.getLeft() != null ? node.getLeft() : node.getRight();
    if (child != null) {
      child.setParent(node.getParent());
    }
    if (node == root) {
      root = child;
    } else {
      Node<E> parent = node.getParent();
      if (node == parent.getLeft()) {
        parent.setLeft(child);
      } else {
        parent.setRight(child);
      }
    }
    size--;
    E temp = node.getElement();
    node.setParent(null);
    node.setLeft(null);
    node.setRight(null);
    node.setElement(null);
    return temp;
  }

  // ----------- end of update methods supported by this class ------------

  // TODO: iterator() and positions() implementation
  @Override
  public Iterator<E> iterator() {

    return null;
  }

  @Override
  public Iterable<Position<E>> positions() {
    return null;
  }
}
