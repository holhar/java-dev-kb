package de.holhar.java_dev_kb.data_structures_and_algorithms.ch04_stacks_queues_deques.s1_stacks;

import de.holhar.java_dev_kb.data_structures_and_algorithms.ch01_fundamental_data_structures.s2_singly_linked_lists.SinglyLinkedList;

public class LinkedStack<E> implements Stack<E> {

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
  public void push(E e) {
    list.addFirst(e);
  }

  @Override
  public E top() {
    return list.first();
  }

  @Override
  public E pop() {
    return list.removeFirst();
  }
}
