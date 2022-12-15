package de.holhar.java_dev_kb.data_structures_and_algorithms.ch07_priority_queues.s1_prioty_queue;

import java.util.Comparator;

public class DefaultComparator<E> implements Comparator<E> {

  @Override
  public int compare(E a, E b) throws ClassCastException {
    return ((Comparable<E>)a).compareTo(b);
  }
}
