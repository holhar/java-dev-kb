package de.holhar.java_dev_kb.data_structures_and_algorithms.ch08_maps_hashtables_skiplists.s3_sorted_maps;

import de.holhar.java_dev_kb.data_structures_and_algorithms.ch07_priority_queues.s1_prioty_queue.Entry;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortedTableMap<K,V> extends AbstractSortedMap<K,V> {

  private List<MapEntry<K,V>> table = new ArrayList<>();

  public SortedTableMap() {
    super();
  }

  public SortedTableMap(Comparator<K> comp) {
    super(comp);
  }

  /**
   * Returns the smallest index for range table[low..high] inclusive storing an entry with a key
   * greater than or equal to k (or else index high+1, by convention).
   */
  private int findIndex(K key, int low, int high) {
    // no entry qualifies
    if (high < low)
      return high+1;

    int mid = (low + high) / 2;

    int comp = compare(key, table.get(mid));
    if (comp == 0)
      return mid;                                   // found exact match
    else if (comp < 0)
      return findIndex(key, low, mid - 1);    // answer is left of mid (or possibly mid)
    else
      return findIndex(key, mid + 1, high);   // answer is right of mid
  }

  /**
   * Version of findIndex that searches the entire table
   */
  private int findIndex(K key) {
    return findIndex(key, 0, table.size() - 1);
  }

  /**
   * Returns the number of entries in the map.
   */
  public int size() {
    return table.size();
  }

  /**
   * Returns the value associated with the specified key (or else null).
   */
  public V get(K key) {
    int j = findIndex(key);
    if (j == size() || compare(key, table.get(j)) != 0)   // no match
      return null;
    return table.get(j).getValue();
  }

  /**
   * Associates the given value with the given key, returning any overridden value.
   */
  public V put(K key, V value) {
    int j = findIndex(key);
    if (j < size() && compare(key, table.get(j)) == 0)    // match exists
      return table.get(j).setValue(value);
    table.add(j, new MapEntry<>(key, value));             // otherwise new
    return null;
  }

  /**
   * Removes the entry having key k (if any) and returns its associated value.
   */
  public V remove(K key) {
    int j = findIndex(key);
    if (j == size() || compare(key, table.get(j)) != 0)   // no match
      return null;
    return table.remove(j).getValue();
  }

  /**
   * Utility returns the entry at index j, or else null if j is out of bounds.
   */
  private MapEntry<K,V> safeEntry(int j) {
    if (j < 0 || j >= table.size())   
      return null;
    return table.get(j);
  }
  
  /**
   * Returns the entry with smallest key value (or null, if the map is empty).
   */
  public MapEntry<K,V> firstEntry() {
    return safeEntry(0);
  }

  /**
   * Returns the entry with largest key value (or null, if the map is empty).
   */
  public MapEntry<K,V> lastEntry() {
    return safeEntry(size() - 1);
  }

  /**
   * Returns the entry with the least key value greater than or equal to k (or null, if no such
   * entry exists).
   */
  public MapEntry<K,V> ceilingEntry(K key) {
    return safeEntry(findIndex(key));
  }

  /**
   * Returns the entry with the greatest key value less than or equal to k (or null, if no such
   * entry exists).
   */
  public MapEntry<K,V> floorEntry(K key) {
    int j = findIndex(key);
    if (j == size() || !key.equals(table.get(j).getKey()))
      j--;    // look one earlier (unless we had found a perfect match
    return safeEntry(j);
  }

  /**
   * Returns the entry with the greatest key value strictly less than k (or null, if no such
   * entry exists).
   */
  public MapEntry<K,V> lowerEntry(K key) {
    return safeEntry(findIndex(key) - 1);     // go strictly before the ceiling entry
  }

  /**
   * Returns the entry with the least key value strictly greater than k (or null if no such entry
   * exists).
   */
  public MapEntry<K,V> higherEntry(K key) {
    int j = findIndex(key);
    if (j < size() && key.equals(table.get(j).getKey()))
      j++;    // go past exact match
    return safeEntry(j);
  }

  // support for snapshot iterators for entrySet() and subMap() follow
  private Iterable<MapEntry<K,V>> snapshot(int startIndex, K stop) {
    List<MapEntry<K,V>> buffer = new ArrayList<>();
    int j = startIndex;
    while (j < size() && (stop == null || compare(stop, table.get(j)) > 0))
      buffer.add(table.get(j++));
    return buffer;
  }

  public Iterable<Entry<K,V>> entrySet() {
    Iterable<MapEntry<K, V>> buffer = snapshot(0, null);
    List<Entry<K,V>> entries = new ArrayList<>();
    buffer.forEach(entry -> entries.add((Entry<K,V>)entry));
    return entries;
  }

  /**
   * Returns an iteration of all entries with key greater than or equal to k1 , but strictly less
   * than k2 .
   */
  public Iterable<MapEntry<K,V>> subMap(K fromKey, K toKey) {
    return snapshot(findIndex(fromKey), toKey);
  }
}
