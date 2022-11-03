package de.holhar.java_dev_kb.training.concurrency.ch06_task_execution.s6_2_executor_framework;

/**
 * Executor interface (shown here for illustration).
 */
public interface ExecutorInterface {
  void execute(Runnable command);
}
