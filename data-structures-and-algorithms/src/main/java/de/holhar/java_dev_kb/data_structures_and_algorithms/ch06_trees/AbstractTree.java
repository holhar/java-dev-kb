package de.holhar.java_dev_kb.data_structures_and_algorithms.ch06_trees;

public abstract class AbstractTree<E> implements Tree<E> {

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
}
