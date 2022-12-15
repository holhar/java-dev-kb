package de.holhar.java_dev_kb.data_structures_and_algorithms.ch07_priority_queues.s1_prioty_queue;

import de.holhar.java_dev_kb.data_structures_and_algorithms.ch05_list_and_iterator_ADTs.s3_positional_lists.Position;
import de.holhar.java_dev_kb.data_structures_and_algorithms.ch05_list_and_iterator_ADTs.s4_iterators.IterablePositionalList;
import java.util.Comparator;

/**
 * An implementation of a priority queue with an unsorted list.
 */
public class UnsortedPriorityQueue<K,V> extends AbstractPriorityQueue<K,V> {

  /**
   * primary collection of priority queue entries
   */
  private IterablePositionalList<Entry<K,V>> list = new IterablePositionalList<>();

  /**
   * Creates an empty priority queue based on the natural ordering its keys.
   */
  public UnsortedPriorityQueue() {
    super();
  }

  /**
   * Creates an empty priority queue using the given comparator to order keys.
   */
  public UnsortedPriorityQueue(Comparator<K> comp) {
    super(comp);
  }

  /**
   * Returns the Position of an entry having minimal key.
   */
  private Position<Entry<K,V>> findMin() {
    Position<Entry<K,V>> small = list.first();
    for (Position<Entry<K,V>> walk : list.positions()) {
      if ((compare(walk.getElement(), small.getElement()) < 0))
        small = walk;
    }
    return small;
  }

  /**
   * Inserts a key-value pair and returns the entry created.
   */
  @Override
  public Entry<K,V> insert(K key, V value) throws IllegalArgumentException {
    checkKey(key);
    Entry<K,V> newest = new PQEntry<>(key, value);
    list.addLast(newest);
    return newest;
  }

  /**
   * Returns (but does not remove) an entry with minimal key.
   */
  @Override
  public Entry<K, V> min() {
    if (list.isEmpty())
      return null;
    return findMin().getElement();
  }

  /**
   * Removes and returns an entry with minimal key.
   */
  @Override
  public Entry<K, V> removeMin() {
    if (list.isEmpty())
      return null;
    return list.remove(findMin());
  }

  /**
   * Returns the number of items in the priority queue.
   */
  @Override
  public int size() {
    return list.size();
  }
}
