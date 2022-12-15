package de.holhar.java_dev_kb.data_structures_and_algorithms.ch07_priority_queues.s4_adaptable_priority_queues;

import de.holhar.java_dev_kb.data_structures_and_algorithms.ch07_priority_queues.s1_prioty_queue.Entry;
import de.holhar.java_dev_kb.data_structures_and_algorithms.ch07_priority_queues.s2_heaps.HeapPriorityQueue;
import java.util.Comparator;

/**
 * An implementation of an adaptable priority queue using an array-based heap.
 */
public class HeapAdaptablePriorityQueue<K,V> extends HeapPriorityQueue<K,V> implements AdaptablePriorityQueue<K,V> {

  // --------------------- nested AdaptablePQEntry class -----------------------------

  /**
   * Extension of the PQEntry to include location information.
   */
  protected static class AdaptablePQEntry<K,V> extends PQEntry<K,V> {

    // entry's current index within the heap
    private int index;

    public AdaptablePQEntry(K key, V value, int j) {
      super(key, value);  // sets key and value
      index = j;          // sets new field
    }

    public int getIndex() {
      return index;
    }

    public void setIndex(int index) {
      this.index = index;
    }
  }
  // ------------------ end of nested AdaptablePQEntry class -------------------------

  /**
   * Creates an empty adaptable priority queue based on the natural ordering its keys.
   */
  public HeapAdaptablePriorityQueue() {
    super();
  }

  /**
   * Creates an empty adaptable priority queue using the given comparator to order keys.
   */
  public HeapAdaptablePriorityQueue(Comparator<K> comp) {
    super(comp);
  }

  // protected utilites
  /**
   * Validates an entry to ensure it is location-aware.
   */
  protected AdaptablePQEntry<K,V> validate(Entry<K,V> entry) throws IllegalArgumentException {
    if (!(entry instanceof AdaptablePQEntry))
      throw new IllegalArgumentException("Invalid entry");
    AdaptablePQEntry<K,V> locator = (AdaptablePQEntry<K, V>) entry; // safe
    int j = locator.getIndex();
    if (j >= heap.size() || heap.get(j) != locator)
      throw new IllegalArgumentException("Invalid entry");
    return locator;
  }

  protected void swap(int i, int j) {
    super.swap(i, j);                                   // perform swat
    ((AdaptablePQEntry<K,V>) heap.get(i)).setIndex(i);  // reset entry's index
    ((AdaptablePQEntry<K,V>) heap.get(j)).setIndex(j);  // reset entry's index
  }

  /**
   * Restores the heap property by moving the entry at index j upward/downward.
   */
  protected void bubble(int j) {
    if (j > 0 && compare(heap.get(j), heap.get(parent(j))) < 0)
      upheap(j);
    else
      downheap(j);    // although it might not need to move
  }

  @Override
  public Entry<K,V> insert(K key, V value) throws IllegalArgumentException {
    checkKey(key);
    AdaptablePQEntry<K, V> newest = new AdaptablePQEntry<>(key, value, heap.size());
    heap.add(newest);
    upheap(heap.size() - 1);
    return newest;
  }

  @Override
  public void remove(Entry<K, V> entry) throws IllegalArgumentException {
    AdaptablePQEntry<K, V> locator = validate(entry);
    int j = locator.getIndex();
    if (j == heap.size() - 1)
      heap.remove(heap.size() - 1);
    else {
      swap(j, heap.size() - 1);
      heap.remove(heap.size() - 1);
      bubble(j);
    }
  }

  @Override
  public void replaceKey(Entry<K, V> entry, K key) throws IllegalArgumentException {
    AdaptablePQEntry<K, V> locator = validate(entry);
    checkKey(key);
    locator.setKey(key);
    bubble(locator.getIndex());
  }

  @Override
  public void replaceValue(Entry<K, V> entry, V value) throws IllegalArgumentException {
    AdaptablePQEntry<K, V> locator = validate(entry);
    locator.setValue(value);
  }
}
