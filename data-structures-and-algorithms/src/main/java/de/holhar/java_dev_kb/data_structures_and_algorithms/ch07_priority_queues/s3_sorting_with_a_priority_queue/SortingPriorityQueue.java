package de.holhar.java_dev_kb.data_structures_and_algorithms.ch07_priority_queues.s3_sorting_with_a_priority_queue;

import de.holhar.java_dev_kb.data_structures_and_algorithms.ch05_list_and_iterator_ADTs.s4_iterators.IterablePositionalList;
import de.holhar.java_dev_kb.data_structures_and_algorithms.ch07_priority_queues.s1_prioty_queue.PriorityQueue;

public class SortingPriorityQueue {

  private SortingPriorityQueue() {
  }

  /**
   * Sorts sequence S, using initially empty priority queue P to produce the order.
   */
  public static <E> void pgSort(IterablePositionalList<E> s, PriorityQueue<E,?> p) {
    int n = s.size();
    for (int j = 0; j < n; j++) {
      E element = s.remove(s.first());
      p.insert(element, null);          // element is key; null value
    }
    for (int j = 0; j < n; j++) {
      E element = p.removeMin().getKey();
      s.addLast(element);
    }
  }
}
