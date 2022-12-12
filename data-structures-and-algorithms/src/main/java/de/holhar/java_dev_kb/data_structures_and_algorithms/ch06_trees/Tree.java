package de.holhar.java_dev_kb.data_structures_and_algorithms.ch06_trees;

import java.util.Iterator;

/**
 * An interface for a tree where nodes can have an arbitrary number of children.
 */
public interface Tree<E> {

  /**
   * Returns the position of the root of the tree (or null if empty).
   */
  Position<E> root();

  /**
   * Returns the position of the parent of the position p (or null if empty).
   */
  Position<E> parent(Position<E> p) throws IllegalArgumentException;

  /**
   * Returns an iterable collection containing the children of position p (if any).
   *
   * If a tree T is ordered, then children(p) reports the children of p in order.
   */
  Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException;

  /**
   * Returns the number of children of position p.
   */
  int numChildren(Position<E> p) throws IllegalArgumentException;

  /**
   * Returns true if position p has at least one child.
   */
  boolean isInternal(Position<E> p) throws IllegalArgumentException;

  /**
   * Returns true if position p does not have any children.
   */
  boolean isExternal(Position<E> p) throws IllegalArgumentException;

  /**
   * Returns true if position p is the root of the tree.
   */
  boolean isRoot(Position<E> p) throws IllegalArgumentException;

  /**
   * Returns the number of positions (and hence elements) that are contained in the tree.
   */
  int size();

  /**
   * Returns true if the tree does not contain any positions (and this has no elements).
   */
  boolean isEmpty();

  /**
   * Returns an iterator for all elements in the tree (so that the tree itself is Iterable).
   */
  Iterator<E> iterator();

  /**
   * Returns an iterable collection to any method of a tree.
   */
  Iterable<Position<E>> positions();
}
