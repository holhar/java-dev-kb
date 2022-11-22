package de.holhar.java_dev_kb.training.concurrency.ch13_explicit_locks.s13_1_lock_and_reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Guarding object state using ReentrantLock.
 */
public class GuardingObjectState {

  public void doSomething() {
    Lock lock = new ReentrantLock();
    // ...
    lock.lock();
    try {
      // Update object state
      // Catch exceptions and restore invariants if necessary
    } finally {
      lock.unlock();
    }
  }
}
