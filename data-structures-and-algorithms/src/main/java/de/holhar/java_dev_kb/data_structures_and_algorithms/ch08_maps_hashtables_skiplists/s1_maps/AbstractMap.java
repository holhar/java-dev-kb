package de.holhar.java_dev_kb.data_structures_and_algorithms.ch08_maps_hashtables_skiplists.s1_maps;

import de.holhar.java_dev_kb.data_structures_and_algorithms.ch07_priority_queues.s1_prioty_queue.Entry;
import java.util.Iterator;

public abstract class AbstractMap<K,V> implements Map<K,V> {

  public boolean isEmpty() {
    return size() == 0;
  }

  // --------------- nested MapEntry class -----------------
  protected static class MapEntry<K,V> implements Entry<K,V> {

    private K key;
    private V value;

    public MapEntry(K key, V value) {
      this.key = key;
      this.value = value;
    }

    @Override
    public K getKey() {
      return null;
    }

    @Override
    public V getValue() {
      return null;
    }

    protected void setKey(K key) {
      this.key = key;
    }

    public V setValue(V value) {
      V old = this.value;
      this.value = value;
      return old;
    }
  }
  // -----------end of nested MapEntry class ---------------

  // Support for public keySet method...
  private class KeyIterator implements Iterator<K> {

    private final Iterator<Entry<K,V>> entries = entrySet().iterator();   // reuse entrySet

    @Override
    public boolean hasNext() {
      return entries.hasNext();
    }

    @Override
    public K next() {
      return entries.next().getKey();   // return key!
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  private class KeyIterable implements Iterable<K> {
    public Iterator<K> iterator() {
      return new KeyIterator();
    }
  }

  public Iterable<K> keySet() {
    return new KeyIterable();
  }

  // Support for public values method...
  private class ValueIterator implements Iterator<V> {

    private final Iterator<Entry<K,V>> entries = entrySet().iterator();   // reuse entrySet

    @Override
    public boolean hasNext() {
      return entries.hasNext();
    }

    @Override
    public V next() {
      return entries.next().getValue();
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  private class ValueIterable implements Iterable<V> {
    public Iterator<V> iterator() {
      return new ValueIterator();
    }
  }

  public Iterable<V> values() {
    return new ValueIterable();
  }
}
