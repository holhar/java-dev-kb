package de.holhar.java_dev_kb.data_structures_and_algorithms.ch05_list_and_iterator_ADTs.s3_positional_lists;

public interface Position<E> {
  E getElement() throws IllegalStateException;
}
