package de.holhar.java_dev_kb.training.concurrency.ch14_custom_synchronizers.s14_3_explicit_condition_objects;

import de.holhar.java_dev_kb.training.concurrency.utils.GuardedBy;
import de.holhar.java_dev_kb.training.concurrency.utils.ThreadSafe;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Bounded buffer using explicit condition variables.
 */
@ThreadSafe
public class ConditionBoundedBuffer<T> {

  private static final int BUFFER_SIZE = 1000;

  protected final Lock lock = new ReentrantLock();

  // CONDITION PREDICATE: notFull (count < item.length)
  private final Condition notFull = lock.newCondition();

  // CONDITION PREDICATE: notEmpty (count > 0)
  private final Condition notEmpty = lock.newCondition();

  @GuardedBy("lock")
  private final T[] items = (T[]) new Object[BUFFER_SIZE];

  @GuardedBy("lock")
  private int tail, head, count;

  // BLOCKS-UNTIL: notFull
  public void put(T x) throws InterruptedException {
    lock.lock();
    try {
      while (count == items.length) {
        notFull.await();
      }
      items[tail] = x;
      if (++tail == items.length) {
        tail = 0;
      }
      ++count;
      notEmpty.signal();
    } finally {
      lock.unlock();
    }
  }

  // BLOCKS-UNTIL: notEmpty
  public T take() throws InterruptedException {
    lock.lock();
    try {
      while (count == 0) {
        notEmpty.await();
      }
      T x = items[head];
      items[head] = null;
      if (++head == items.length) {
        head = 0;
      }
      --count;
      notFull.signal();
      return x;
    } finally {
      lock.unlock();
    }
  }
}
