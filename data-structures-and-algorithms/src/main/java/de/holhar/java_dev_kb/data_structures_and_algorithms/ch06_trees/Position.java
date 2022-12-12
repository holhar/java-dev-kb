package de.holhar.java_dev_kb.data_structures_and_algorithms.ch06_trees;

public interface Position<E> {

  /**
   * Returns the element stored at this position.
   */
  E getElement() throws IllegalStateException;
}
