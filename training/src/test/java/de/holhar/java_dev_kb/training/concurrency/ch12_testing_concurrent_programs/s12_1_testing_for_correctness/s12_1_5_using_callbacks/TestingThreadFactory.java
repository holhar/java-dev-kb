package de.holhar.java_dev_kb.training.concurrency.ch12_testing_concurrent_programs.s12_1_testing_for_correctness.s12_1_5_using_callbacks;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Thread factory for testing ThreadPoolExecutor.
 */
public class TestingThreadFactory implements ThreadFactory {

  public final AtomicInteger numCreated = new AtomicInteger();
  private final ThreadFactory factory = Executors.defaultThreadFactory();

  @Override
  public Thread newThread(Runnable r) {
    numCreated.incrementAndGet();
    return factory.newThread(r);
  }
}
