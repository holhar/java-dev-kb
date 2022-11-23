package de.holhar.java_dev_kb.training.concurrency.ch14_custom_synchronizers.s14_2_using_condition_queues.s14_2_5_example_gate_class;

import de.holhar.java_dev_kb.training.concurrency.utils.GuardedBy;
import de.holhar.java_dev_kb.training.concurrency.utils.ThreadSafe;

/**
 * Recloseable gate using wait and notifyAll.
 */
@ThreadSafe
public class ThreadGate {

  // CONDITION-PREDICATE: opened-since(n) (isOpen || generation > n)
  @GuardedBy("this")
  private boolean isOpen;

  @GuardedBy("this")
  private int generation;

  public synchronized void close() {
    isOpen = false;
  }

  public synchronized void open() {
    ++generation;
    isOpen = true;
    notifyAll();
  }

  // BLOCKS-UNTIL: opened-since(generation on entry)
  public synchronized void await() throws InterruptedException {
    int arrivalGeneration = generation;
    while (!isOpen && arrivalGeneration == generation) {
      wait();
    }
  }
}
