package de.holhar.java_dev_kb.data_structures_and_algorithms.ch06_trees;

/**
 * An interface for a binary tree, in which each node has at most two children.
 *
 * A binary tree is an ordered tree with the following properties:
 * - every node has at most two children
 * - each child node is labeled as being either a left child (left subtree) or a right child (right subtree)
 * - a left child precedes a right child in the order of children of a node
 *
 * A binary tree is proper (a fully binary tree) if each node has either zero or two children, it
 * is improper otherwise.
 */
public interface BinaryTree<E> extends Tree<E> {

  /**
   * Returns the Position of p's left child (of null if no child exists).
   */
  Position<E> left(Position<E> p) throws IllegalArgumentException;

  /**
   * Returns the Position of p's right child (of null if no child exists).
   */
  Position<E> right(Position<E> p) throws IllegalArgumentException;

  /**
   * Returns the Position of p's sibling (of null if no child exists).
   */
  Position<E> sibling(Position<E> p) throws IllegalArgumentException;
}
