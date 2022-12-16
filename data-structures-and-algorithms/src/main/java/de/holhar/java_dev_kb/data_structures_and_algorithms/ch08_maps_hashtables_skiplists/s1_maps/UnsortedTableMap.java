package de.holhar.java_dev_kb.data_structures_and_algorithms.ch08_maps_hashtables_skiplists.s1_maps;

import de.holhar.java_dev_kb.data_structures_and_algorithms.ch05_list_and_iterator_ADTs.s2_array_lists.ArrayList;
import de.holhar.java_dev_kb.data_structures_and_algorithms.ch07_priority_queues.s1_prioty_queue.Entry;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class UnsortedTableMap<K,V> extends AbstractMap<K,V> {

  /**
   * Underlying storage for the map of entries.
   */
  private final ArrayList<MapEntry<K,V>> table = new ArrayList<>();

  // private utility
  /**
   * Returns the index of an entry with equal key, or -1 if none found.
   */
  private int findIndex(K key) {
    int n = table.size();
    for (int j=0; j < n; j++) {
      if (table.get(j).getKey().equals(key)) {
        return j;
      }
    }
    return -1;
  }

  @Override
  public int size() {
    return table.size();
  }

  @Override
  public V get(K key) {
    int j = findIndex(key);
    if (j == -1) {
      return null;
    }
    return table.get(j).getValue();
  }

  @Override
  public V put(K key, V value) {
    int j = findIndex(key);
    if (j == -1) {
      table.add(j, new MapEntry<>(key, value));
      return null;
    } else {
      return table.get(j).setValue(value);
    }
  }

  @Override
  public V remove(K key) {
    int j = findIndex(key);
    int n = size();
    if (j == -1)
      return null;

    V answer = table.get(j).getValue();
    if (j != n - 1)
      // relocate last entry to 'hole' created by removal
      table.set(j, table.get(n - 1));
    table.remove(n-1);
    return answer;
  }

  private class EntryIterator implements Iterator<Entry<K,V>> {

    private int j;

    @Override
    public boolean hasNext() {
      return j < table.size();
    }

    @Override
    public Entry<K, V> next() {
      if (j == table.size())
        throw new NoSuchElementException();
      return table.get(j++);
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  private class EntryIterable implements Iterable<Entry<K,V>> {

    @Override
    public Iterator<Entry<K, V>> iterator() {
      return new EntryIterator();
    }
  }

  @Override
  public Iterable<Entry<K, V>> entrySet() {
    return new EntryIterable();
  }
}
