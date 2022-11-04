package de.holhar.java_dev_kb.training.concurrency.ch10_avoiding_liveness_hazards.s10_1_deadlock.s10_1_1_lock_ordering_deadlocks;

/**
 * Simple lock-ordering deadlock.
 * DON'T DO THIS.
 *
 * Warning: deadlock-prone!
 */
public class LeftRightDeadlock {

  private final Object left = new Object();
  private final Object right = new Object();

  public void leftRigt() {
    synchronized (left) {
      synchronized (right) {
        doSomething();
      }
    }
  }

  public void rightLeft() {
    synchronized (right) {
      synchronized (left) {
        doSomethingElse();
      }
    }
  }

  private void doSomething() {
    // ...
  }

  private void doSomethingElse() {
    // ...
  }
}
