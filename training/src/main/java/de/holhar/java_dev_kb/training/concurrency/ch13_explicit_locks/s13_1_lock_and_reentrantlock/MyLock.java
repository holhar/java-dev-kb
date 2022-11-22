package de.holhar.java_dev_kb.training.concurrency.ch13_explicit_locks.s13_1_lock_and_reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

/**
 * Lock interface (shown here for illustration purposes).
 */
public interface MyLock {
  void lock();
  void lockInterruptibly() throws InterruptedException;
  boolean tryLock();
  boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException;
  void unlock();
  Condition newCondition();
}
