package de.holhar.java_dev_kb.training.concurrency.ch07_cancellation_and_shutdown.s7_1_task_cancellation.s7_1_3_responding_to_interruption;

import java.util.concurrent.BlockingDeque;
import org.springframework.scheduling.config.Task;

/**
 * Propagating InterruptedException to callers.
 */
public class ProgateInterruptedException {

  BlockingDeque<Task> queue;

  // ...

  public Task getNextTask() throws InterruptedException {
    return queue.take();
  }
}
