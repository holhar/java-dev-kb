package de.holhar.java_dev_kb.data_structures_and_algorithms.ch08_maps_hashtables_skiplists.s2_hash_tables;

import de.holhar.java_dev_kb.data_structures_and_algorithms.ch07_priority_queues.s1_prioty_queue.Entry;
import java.util.ArrayList;
import java.util.List;

public class ProbeHashMap<K,V> extends AbstractHashMap<K,V> {

  // a fixed array of entries (all initially null)
  private MapEntry<K,V>[] table;

  // sentinel
  private MapEntry<K,V> DEFUNCT = new MapEntry<>(null, null);

  public ProbeHashMap() {
    super();
  }

  public ProbeHashMap(int capacity) {
    super(capacity);
  }

  public ProbeHashMap(int capacity, int prime) {
    super(capacity, prime);
  }

  /**
   * Creates an empty table having length equal to current capacity.
   */
  @Override
  protected void createTable() {
    table = (MapEntry<K, V>[]) new MapEntry[capacity];
  }

  /**
   * Returns true if location is either empty or the "defunct" sentinel.
   */
  private boolean isAvailable(int j) {
    return (table[j] == null || table[j] == DEFUNCT);
  }

  /**
   * Returns index with key k, or -(a+1) such that k could be added at index a.
   */
  private int findSlot(int h, K k) {
    int available = -1;                               // no slot available (thus far)
    int j = h;                                        // index while scanning table
    do {
      if (isAvailable(j)) {                           // may be either empty or defunct
        if (available == -1) {                        // this is the first available slot!
          available = j;
        }
        if (table[j] == null) {                       // if empty, search fails immediately
          break;
        }
      } else if (table[j].getKey().equals(k)) {       // successful match
        return j;
      }
      j = (j+1) % capacity;                           // keep looking (cyclically)
    } while (j != h);                                 // stop if we return to the start
    return -(available + 1);                          // search has failed
  }

  /**
   * Returns value associated with key k in bucket with hash value h, or else null.
   */
  @Override
  protected V bucketGet(int h, K k) {
    int j = findSlot(h, k);
    if (j < 0)
      return null;
    return table[j].getValue();
  }

  /**
   * Associates key k with value v in bucket with hash value h; returns old value.
   */
  @Override
  protected V bucketPut(int h, K k, V v) {
    int j = findSlot(h, k);

    // key has existing entry
    if (j >= 0) {
      return table[j].setValue(v);
    }

    // convert to proper index
    table[-(j+1)] = new MapEntry<>(k, v);
    n++;
    return null;
  }

  /**
   * Removes entry having key k from bucket with hash value h (if any).
   */
  @Override
  protected V bucketRemove(int h, K k) {
    int j = findSlot(h, k);

    // nothing to remove
    if (j < 0)
      return null;

    V answer = table[j].getValue();
    // mark slot as deactivated
    table[j] = DEFUNCT;
    n--;
    return answer;
  }

  /**
   * Returns an iterable collection of all key-value entries of the map.
   */
  @Override
  public Iterable<Entry<K, V>> entrySet() {
    List<Entry<K,V>> buffer = new ArrayList<>();
    for (int h = 0; h < capacity; h++)
      if (!isAvailable(h))
        buffer.add(table[h]);
    return buffer;
  }
}
