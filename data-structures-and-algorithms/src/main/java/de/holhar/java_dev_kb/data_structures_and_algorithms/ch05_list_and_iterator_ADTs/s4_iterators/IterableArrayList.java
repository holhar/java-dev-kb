package de.holhar.java_dev_kb.data_structures_and_algorithms.ch05_list_and_iterator_ADTs.s4_iterators;

import de.holhar.java_dev_kb.data_structures_and_algorithms.ch05_list_and_iterator_ADTs.s1_the_list_ADT.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IterableArrayList<E> implements List<E> {

  // ---------------------- start of nested ArrayIterator class ------------------------
  /**
   * A (non-static) inner class. Note well that each instance contains an implicit reference to
   * an implicit reference to the containing list, allowing it to access the list's members.
   */
  private class ArrayIterator implements Iterator<E> {

    // index of the next element to report
    private int j;

    // can remove be called at this time?
    private boolean removable;

    /**
     * Tests whether the iterator has a next object.
     * @return true if there are further objects, false otherwise
     */
    @Override
    public boolean hasNext() {
      return j < size;
    }

    /**
     * Returns the next object in the iterator.
     *
     * @return next object
     * @throws NoSuchElementException if there are no further elements.
     */
    @Override
    public E next() throws NoSuchElementException {
      if (j == size)
        throw new NoSuchElementException("No next element");
      removable = true;
      return data[j++];
    }

    /**
     * Removes the element returned by most recent call to next.
     *
     * @throws IllegalStateException if next has not yet been called
     * @throws IllegalStateException if remove was already called since recent recent next
     */
    @Override
    public void remove() throws IllegalStateException {
      if (!removable)
        throw new IllegalStateException("Nothing to remove");
      IterableArrayList.this.remove(j - 1); // that was the last one returned
      j--;                                    // next element has shifted one cell to the left
      removable = false;                      // do not allow remove again until next is called
    }
  }
  // ---------------------- end of nested ArrayIterator class ------------------------

  /**
   * Returns an iterator of the elements stored in the list.
   */
  public Iterator<E> iterator() {
    return new ArrayIterator(); // create new instance of inner class
  }

  public static final int CAPACITY = 16;
  private E[] data;
  private int size;

  public IterableArrayList() {
    this(CAPACITY);
  }

  public IterableArrayList(int capacity) {
    data = (E[]) new Object[capacity];
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public E get(int i) throws IndexOutOfBoundsException {
    checkIndex(i, size);
    return data[i];
  }

  @Override
  public E set(int i, E e) throws IndexOutOfBoundsException {
    checkIndex(i, size);
    E temp = data[i];
    data[i] = e;
    return temp;
  }

  @Override
  public void add(int i, E e) throws IndexOutOfBoundsException {
    checkIndex(i, size);
    if (size == data.length)
      resize(2 * data.length);

    for (int k = size - 1; k >= i; k--) {
      data[k + 1] = data[k];
    }
    data[i] = e;
    size++;
  }

  @Override
  public E remove(int i) throws IndexOutOfBoundsException {
    checkIndex(i, size);
    E temp = data[i];
    for (int k = i; i < size - 1; k++) {
      data[k] = data[k + 1];
    }
    size--;
    return temp;
  }

  private void checkIndex(int i, int n) throws IndexOutOfBoundsException {
    if (i < 0 || i > n)
      throw new IndexOutOfBoundsException("Illegal index: " + i);
  }

  private void resize(int capacity) {
    E[] temp = (E[]) new Object[capacity];
    for (int i = 0; i < data.length; i++) {
      temp[i] = data[i];
    }
    data = temp;
  }
}
