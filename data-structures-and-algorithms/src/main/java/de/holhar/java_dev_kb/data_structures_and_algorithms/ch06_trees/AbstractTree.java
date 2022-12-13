package de.holhar.java_dev_kb.data_structures_and_algorithms.ch06_trees;

import de.holhar.java_dev_kb.data_structures_and_algorithms.ch04_stacks_queues_deques.s2_queues.LinkedQueue;
import de.holhar.java_dev_kb.data_structures_and_algorithms.ch04_stacks_queues_deques.s2_queues.Queue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractTree<E> implements Tree<E> {

  // --------------- start of nested ElementIterator class ---------------------
  /* This class adapts the iteration produced by positions() to return elements. */
  private class ElementIterator implements Iterator<E> {

    Iterator<Position<E>> posIterator = positions().iterator();

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
  // ---------------- end of nested ElementIterator class ----------------------

  @Override
  public boolean isInternal(Position<E> p) throws IllegalArgumentException {
    return numChildren(p) > 0;
  }

  @Override
  public boolean isExternal(Position<E> p) throws IllegalArgumentException {
    return numChildren(p) == 0;
  }

  @Override
  public boolean isRoot(Position<E> p) throws IllegalArgumentException {
    return p == root();
  }

  @Override
  public boolean isEmpty() {
    return size() == 0;
  }

  /**
   * Returns the number of levels separating Position p from the root.
   *
   * Runs in O(n) time.
   */
  public int depth(Position<E> p) {
    if (isRoot(p))
      return 0;
    else
      return 1 + depth(parent(p));
  }

  /**
   * Returns the height of the tree.
   *
   * Runs in O(n^2) time (which is bad and that's why this method is private).
   */
  private int heightBad() {
    int h = 0;
    for (Position<E> p : positions()) {
      // Only consider leaf positions
      if (isExternal(p))
        h = Math.max(h, depth(p));
    }
    return h;
  }

  /**
   * Returns the height of the subtree rooted at Position p.
   * The overall height of a nonempty tree can be computed by sending the root of the tree as a
   * parameter.
   *
   * Runs in O(n) time.
   */
  private int height(Position<E> p) {
    int h = 0;
    // base case if p is external
    for (Position<E> c : children(p)) {
      h = Math.max(h, 1 + height(c));
    }
    return h;
  }

  /**
   * Returns an iterator for all elements in the tree (so that the tree itself is Iterable).
   */
  @Override
  public Iterator<E> iterator() {
    return new ElementIterator();
  }

  /**
   * Returns an iterable collection, in preorder, per default
   */
  @Override
  public Iterable<Position<E>> positions() {
    return preorder();
  }

  /**
   * Returns an iterable collection of positions of the tree, reported in preorder.
   */
  public Iterable<Position<E>> preorder() {
    List<Position<E>> snapshot = new ArrayList<>();
    if (!isEmpty())
      preorderSubtree(root(), snapshot);
    return snapshot;
  }

  /**
   * Adds positions of the subtree rooted at Position p to the given snapshot.
   */
  private void preorderSubtree(Position<E> p, List<Position<E>> snapshot) {
    snapshot.add(p);
    for (Position<E> c : children(p))
      preorderSubtree(c, snapshot);
  }

  /**
   * Returns an iterable collection of positions of the tree, reported in postorder.
   */
  public Iterable<Position<E>> postorder() {
    List<Position<E>> snapshot = new ArrayList<>();
    if (!isEmpty())
      postorderSubtree(root(), snapshot);
    return snapshot;
  }

  /**
   * Adds positions of the subtree rooted at Position p to the given snapshot.
   */
  private void postorderSubtree(Position<E> p, List<Position<E>> snapshot) {
    for (Position<E> c : children(p))
      postorderSubtree(c, snapshot);
    // for postorder, we add position p after exploring subtrees
    snapshot.add(p);
  }

  /**
   * Returns an iterable collection of positions of the tree in breadth-first order.
   */
  public Iterable<Position<E>> breadthfirst() {
    List<Position<E>> snapshot = new ArrayList<>();
    if (!isEmpty()) {
      Queue<Position<E>> fringe = new LinkedQueue<>();
      fringe.enqueue(root());
      while (!fringe.isEmpty()) {
        Position<E> p = fringe.dequeue(); // remove from front of the queue
        snapshot.add(p);                  // report this position
        for (Position<E> c : children(p))
          fringe.enqueue(c);              // add children to back of queue
      }
    }
    return snapshot;
  }
}
