package de.holhar.java_dev_kb.data_structures_and_algorithms.ch04_stacks_queues_deques.s2_queues;

import de.holhar.java_dev_kb.data_structures_and_algorithms.ch01_fundamental_data_structures.s2_singly_linked_lists.SinglyLinkedList;

public class LinkedQueue<E> implements Queue<E> {

  private final SinglyLinkedList<E> list = new SinglyLinkedList<>();

  @Override
  public int size() {
    return list.size();
  }

  @Override
  public boolean isEmpty() {
    return list.isEmpty();
  }

  @Override
  public void enqueue(E e) {
    list.addLast(e);
  }

  @Override
  public E first() {
    return list.first();
  }

  @Override
  public E dequeue() {
    return list.removeFirst();
  }
}
