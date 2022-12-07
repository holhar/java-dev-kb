package de.holhar.java_dev_kb.data_structures_and_algorithms.ch05_list_and_iterator_ADTs.s1_the_list_ADT;

public interface List<E> {
  int size();
  boolean isEmpty();
  E get(int i) throws IndexOutOfBoundsException;
  E set(int i, E e) throws IndexOutOfBoundsException;
  void add(int i, E e) throws IndexOutOfBoundsException;
  E remove(int i) throws IndexOutOfBoundsException;
}
