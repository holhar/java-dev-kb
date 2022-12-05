package de.holhar.java_dev_kb.data_structures_and_algorithms.ch04_stacks_queues_deques.s1_stacks;

public interface Stack<E> {
  int size();
  boolean isEmpty();
  void push(E e);
  E top();
  E pop();
}
