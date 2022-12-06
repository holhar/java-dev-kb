package de.holhar.java_dev_kb.data_structures_and_algorithms.ch04_stacks_queues_deques.s2_queues;

public class ArrayQueue<E> implements Queue<E> {

  private static final int CAPACITY = 1000;

  private E[] data;

  // index of the front element
  private int f;

  private int size;

  public ArrayQueue() {
    this(CAPACITY);
  }

  public ArrayQueue(int capacity) {
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
  public void enqueue(E e) {
    if (size == data.length)
      throw new IllegalStateException("Queue is full");
    int avail = (f + size) % data.length; // use modular arithmetic
    data[avail] = e;
    size++;
  }

  @Override
  public E first() {
    if (isEmpty())
      return null;
    return data[f];
  }

  @Override
  public E dequeue() {
    if (isEmpty())
      return null;
    E answer = data[f];
    data[f] = null;
    f = (f + 1) % data.length;
    size--;
    return answer;
  }
}
