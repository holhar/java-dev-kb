package de.holhar.java_dev_kb.data_structures_and_algorithms.ch04_stacks_queues_deques.s2_queues;

public interface Queue<E> {
  int size();
  boolean isEmpty();
  void enqueue(E e);
  E first();
  E dequeue();
}
