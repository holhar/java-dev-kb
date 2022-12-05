package de.holhar.java_dev_kb.data_structures_and_algorithms.ch04_stacks_queues_deques.s1_stacks;

public class ArrayStack<E> implements Stack<E> {

  private static final int CAPACITY = 1000;

  // generic array used for storage
  private E[] data;

  // index of the top element in stack
  private int t = -1;

  public ArrayStack() {
    this(CAPACITY);
  }

  public ArrayStack(int capacity) {
    data = (E[]) new Object[capacity];
  }

  @Override
  public int size() {
    return t + 1;
  }

  @Override
  public boolean isEmpty() {
    return t == -1;
  }

  @Override
  public void push(E e) throws IllegalStateException {
    if (size() == data.length)
      throw new IllegalStateException("Stack is full");
    // increment t before storing new item
    data[++t] = e;
  }

  @Override
  public E top() {
    if (isEmpty())
      return null;
    return data[t];
  }

  @Override
  public E pop() {
    if (isEmpty())
      return null;
    E answer = data[t];
    data[t] = null;
    t--;
    return answer;
  }
}
