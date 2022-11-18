package de.holhar.java_dev_kb.training.concurrency.ch12_testing_concurrent_programs.s12_1_testing_for_correctness;

import de.holhar.java_dev_kb.training.concurrency.utils.GuardedBy;
import java.util.concurrent.Semaphore;

/**
 * Bounded buffer using Semaphore.
 */
public class BoundedBuffer<E> {

  private final Semaphore availableItems, availableSpaces;

  @GuardedBy("this")
  private final E[] items;

  @GuardedBy("this")
  private int putPosition = 0, takePosition = 0;

  public BoundedBuffer(int capacity) {
    availableItems = new Semaphore(0);
    availableSpaces = new Semaphore(capacity);
    items = (E[]) new Object[capacity];
  }

  public boolean isEmpty() {
    return availableItems.availablePermits() == 0;
  }

  public boolean isFull() {
    return availableSpaces.availablePermits() == 0;
  }

  public void put(E x) throws InterruptedException {
    availableSpaces.acquire();
    doInsert(x);
    availableItems.release();
  }

  public E take() throws InterruptedException {
    availableItems.acquire();
    E item = doExtract();
    availableSpaces.release();
    return item;
  }

  private synchronized void doInsert(E x) {
    int i = putPosition;
    items[i] = x;
    putPosition = (++i == items.length)? 0 : i;
  }

  private synchronized E doExtract() {
    int i = takePosition;
    E x = items[i];
    items[i] = null;
    takePosition = (++i == items.length)? 0 : i;
    return x;
  }
}
