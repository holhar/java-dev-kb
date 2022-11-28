package de.holhar.java_dev_kb.training.concurrency.ch14_custom_synchronizers.s14_3_explicit_condition_objects;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Condition interface (shown here for illustration purposes).
 */
public interface MyCondition {
  void await() throws InterruptedException;
  boolean await(long time, TimeUnit unit) throws InterruptedException;
  long awaitNanos(long nanosTimeout) throws InterruptedException;
  void awaitUninterruptibly();
  boolean awaitUntil(Date deadline) throws InterruptedException;

  void signal();
  void signalAll();
}
