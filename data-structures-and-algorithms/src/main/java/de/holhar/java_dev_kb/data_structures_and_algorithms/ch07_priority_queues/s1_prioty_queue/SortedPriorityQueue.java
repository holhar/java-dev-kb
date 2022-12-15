package de.holhar.java_dev_kb.data_structures_and_algorithms.ch07_priority_queues.s1_prioty_queue;

import de.holhar.java_dev_kb.data_structures_and_algorithms.ch05_list_and_iterator_ADTs.s3_positional_lists.Position;
import de.holhar.java_dev_kb.data_structures_and_algorithms.ch05_list_and_iterator_ADTs.s4_iterators.IterablePositionalList;
import java.util.Comparator;

public class SortedPriorityQueue<K,V> extends AbstractPriorityQueue<K,V> {

  /**
   * primary collection of priority queue entries
   */
  private IterablePositionalList<Entry<K,V>> list = new IterablePositionalList<>();

  /**
   * Creates an empty priority queue based on the natural ordering its keys.
   */
  public SortedPriorityQueue() {
    super();
  }

  /**
   * Creates an empty priority queue using the given comparator to order keys.
   */
  public SortedPriorityQueue(Comparator<K> comp) {
    super(comp);
  }

  /**
   * Inserts a key-value pair and returns the entry created.
   */
  @Override
  public Entry<K,V> insert(K key, V value) throws IllegalArgumentException {
    checkKey(key);
    Entry<K,V> newest = new PQEntry<>(key, value);
    Position<Entry<K, V>> walk = list.last();
    // walk backward, looking for smaller key
    while (walk != null && compare(newest, walk.getElement()) < 0)
      walk = list.before(walk);
    if (walk == null)
      list.addFirst(newest);
    else
      list.addAfter(walk, newest);
    return newest;
  }

  /**
   * Returns (but does not remove) an entry with minimal key.
   */
  @Override
  public Entry<K, V> min() {
    if (list.isEmpty())
      return null;
    return list.first().getElement();
  }

  /**
   * Removes and returns an entry with minimal key.
   */
  @Override
  public Entry<K, V> removeMin() {
    if (list.isEmpty())
      return null;
    return list.remove(list.first());
  }

  /**
   * Returns the number of items in the priority queue.
   */
  @Override
  public int size() {
    return list.size();
  }
}
