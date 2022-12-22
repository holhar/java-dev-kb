package de.holhar.java_dev_kb.data_structures_and_algorithms.ch10_sorting_and_selection.s2_quick_sort;

import de.holhar.java_dev_kb.data_structures_and_algorithms.ch04_stacks_queues_deques.s2_queues.LinkedQueue;
import de.holhar.java_dev_kb.data_structures_and_algorithms.ch04_stacks_queues_deques.s2_queues.Queue;
import java.util.Comparator;

public class QuickSort {

  /**
   * Quick-sort contents of a queue.
   */
  public static <K> void quickSort(Queue<K> s, Comparator<K> comparator) {
    int n = s.size();

    if (n < 2)
      return;

    // divide
    K pivot = s.first();          // using first as arbitrary pivot
    Queue<K> l = new LinkedQueue<>();
    Queue<K> e = new LinkedQueue<>();
    Queue<K> g = new LinkedQueue<>();

    // divide original into l, e, and g
    while (!s.isEmpty()) {
      K element = s.dequeue();
      int c = comparator.compare(element, pivot);
      if (c < 0)
        l.enqueue(element);
      else if (c == 0)
        e.enqueue(element);
      else
        g.enqueue(element);
    }

    // conquer
    quickSort(l, comparator);         // sort elements less than pivot
    quickSort(g, comparator);         // sort elements greater than pivot

    // concatenate results
    while (!l.isEmpty())
      s.enqueue(l.dequeue());
    while (!e.isEmpty())
      s.enqueue(e.dequeue());
    while (!g.isEmpty())
      s.enqueue(g.dequeue());
  }

}
