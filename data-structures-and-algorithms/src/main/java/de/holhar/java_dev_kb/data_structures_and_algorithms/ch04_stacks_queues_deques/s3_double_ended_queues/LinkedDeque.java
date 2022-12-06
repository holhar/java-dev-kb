package de.holhar.java_dev_kb.data_structures_and_algorithms.ch04_stacks_queues_deques.s3_double_ended_queues;

import de.holhar.java_dev_kb.data_structures_and_algorithms.ch01_fundamental_data_structures.s4_doubly_linked_lists.DoublyLinkedList;

public class LinkedDeque<E> implements Deque<E> {

  private final DoublyLinkedList<E> list = new DoublyLinkedList<>();

  @Override
  public int size() {
    return list.size();
  }

  @Override
  public boolean isEmpty() {
    return list.isEmpty();
  }

  @Override
  public E first() {
    return list.first();
  }

  @Override
  public E last() {
    return list.last();
  }

  @Override
  public void addFirst(E e) {
    list.addFirst(e);
  }

  @Override
  public void addLast(E e) {
    list.addLast(e);
  }

  @Override
  public E removeFirst() {
    return list.removeFirst();
  }

  @Override
  public E removeLast() {
    return list.removeLast();
  }
}
