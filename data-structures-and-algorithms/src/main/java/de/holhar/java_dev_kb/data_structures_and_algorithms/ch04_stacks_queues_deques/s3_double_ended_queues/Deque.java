package de.holhar.java_dev_kb.data_structures_and_algorithms.ch04_stacks_queues_deques.s3_double_ended_queues;

public interface Deque<E> {
  int size();
  boolean isEmpty();
  E first();
  E last();
  void addFirst(E e);
  void addLast(E e);
  E removeFirst();
  E removeLast();
}
