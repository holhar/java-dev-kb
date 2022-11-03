package de.holhar.java_dev_kb.training.concurrency.ch06_task_execution.s6_2_executor_framework.s6_2_4_executor_lifecycle;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Lifecycle methods in ExecutorService (shown here for illustration).
 */
public interface ExecutorServiceInterface {
  void shutdown();
  List<Runnable> shutdownNow();
  boolean isShutdown();
  boolean isTerminated();
  boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException;
  // ... additional convencience methods for task submission
}
