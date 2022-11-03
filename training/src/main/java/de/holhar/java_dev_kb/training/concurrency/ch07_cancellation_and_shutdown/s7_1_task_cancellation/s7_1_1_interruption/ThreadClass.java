package de.holhar.java_dev_kb.training.concurrency.ch07_cancellation_and_shutdown.s7_1_task_cancellation.s7_1_1_interruption;

/**
 * Interruption methods in Thread (shown here for illustration).
 */
public class ThreadClass {

  public void interrupt() {
    // ...
  }

  public boolean isInterrupted() {
    // ...
    return false;
  }

  public static boolean interrupted() {
    // ...
    return false;
  }

  // ...
}
