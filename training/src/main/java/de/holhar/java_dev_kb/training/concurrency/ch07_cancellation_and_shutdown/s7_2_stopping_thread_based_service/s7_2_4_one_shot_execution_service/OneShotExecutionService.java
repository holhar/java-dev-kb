package de.holhar.java_dev_kb.training.concurrency.ch07_cancellation_and_shutdown.s7_2_stopping_thread_based_service.s7_2_4_one_shot_execution_service;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Using a private Executor whose lifetime is bounded by a method call.
 */
public class OneShotExecutionService {

  boolean checkMail(Set<String> hosts, long timeout, TimeUnit unit) throws InterruptedException {
    ExecutorService exec = Executors.newCachedThreadPool();
    final AtomicBoolean hasNewMail = new AtomicBoolean(false);
    try {
      for (final String host : hosts) {
        exec.execute(() -> {
          if (checkMail(host))
            hasNewMail.set(true);
        });
      }
    } finally {
      exec.shutdown();
      exec.awaitTermination(timeout, unit);
    }
    return hasNewMail.get();
  }

  private boolean checkMail(String host) {
    // do stuff ...
    return false;
  }
}
