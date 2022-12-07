package de.holhar.java_dev_kb.data_structures_and_algorithms.ch05_list_and_iterator_ADTs.s3_positional_lists;

public interface PositionalList<E> {
  int size();
  boolean isEmpty();
  Position<E> first();
  Position<E> last();
  Position<E> before(Position<E> p) throws IllegalArgumentException;
  Position<E> after(Position<E> p) throws IllegalArgumentException;
  Position<E> addFirst(E e);
  Position<E> addLast(E e);
  Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException;
  Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException;

  /*
   * Replaces the element stored at Position p and returns the replaced element.
   */
  E set(Position<E> p, E e) throws IllegalArgumentException;
  E remove(Position<E> p) throws IllegalArgumentException;
}
