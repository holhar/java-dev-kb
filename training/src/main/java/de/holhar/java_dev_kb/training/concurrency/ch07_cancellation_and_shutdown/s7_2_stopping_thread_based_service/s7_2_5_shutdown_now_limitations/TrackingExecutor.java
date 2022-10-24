package de.holhar.java_dev_kb.training.concurrency.ch07_cancellation_and_shutdown.s7_2_stopping_thread_based_service.s7_2_5_shutdown_now_limitations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * ExecutorService that keeps track of cancelled tasks after shutdown
 */
public class TrackingExecutor extends AbstractExecutorService {

  private final ExecutorService exec;
  private final Set<Runnable> tasksCancelledAtShutdown = Collections.synchronizedSet(new HashSet<>());

  public TrackingExecutor(ExecutorService exec) {
    this.exec = exec;
  }

  public List<Runnable> getCancelledTasks() {
    if (!exec.isTerminated()) {
      throw new IllegalStateException("...");
    }
    return new ArrayList<>(tasksCancelledAtShutdown);
  }

  @Override
  public void execute(Runnable runnable) {
    exec.execute(() -> {
      try {
        runnable.run();
      } finally {
        if (isShutdown() && Thread.currentThread().isInterrupted()) {
          tasksCancelledAtShutdown.add(runnable);
        }
      }
    });
  }

  @Override
  public void shutdown() {
    // ...
  }

  @Override
  public List<Runnable> shutdownNow() {
    return null;
  }

  @Override
  public boolean isShutdown() {
    return false;
  }

  @Override
  public boolean isTerminated() {
    return false;
  }

  @Override
  public boolean awaitTermination(long l, TimeUnit timeUnit) throws InterruptedException {
    return false;
  }
}
