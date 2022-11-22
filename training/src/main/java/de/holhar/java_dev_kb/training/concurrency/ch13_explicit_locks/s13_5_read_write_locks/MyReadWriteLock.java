package de.holhar.java_dev_kb.training.concurrency.ch13_explicit_locks.s13_5_read_write_locks;

import java.util.concurrent.locks.Lock;

/**
 * ReadWriteLock interface (shown here for illustration purposes).
 */
public interface MyReadWriteLock {
  Lock readLock();
  Lock writeLock();
}
