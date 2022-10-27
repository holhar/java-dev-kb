package de.holhar.java_dev_kb.training.concurrency.ch08_applying_thread_pools.s8_4_extending_thread_pool_executor.s8_4_1_add_stats_to_thread_pool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Thread pool extended with logging and timing.
 */
public class TimingThreadPool extends ThreadPoolExecutor {

  private static final Logger logger = LoggerFactory.getLogger(TimingThreadPool.class);

  private final ThreadLocal<Long> startTime = new ThreadLocal<>();
  private final AtomicLong numTasks = new AtomicLong();
  private final AtomicLong totalTime = new AtomicLong();

  public TimingThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime,
      TimeUnit unit, BlockingQueue<Runnable> workQueue) {
    super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
  }

  @Override
  protected void beforeExecute(Thread t, Runnable r) {
    super.beforeExecute(t, r);
    logger.debug("Thread {}: start {}", t, r);
    startTime.set(System.nanoTime());
  }

  @Override
  protected void afterExecute(Runnable r, Throwable t) {
    try {
      long endTime = System.nanoTime();
      long taskTime = endTime - startTime.get();
      numTasks.incrementAndGet();
      totalTime.addAndGet(taskTime);
      logger.debug("Thread {}: end {}, time={}", t, r, taskTime);
    } finally {
      super.afterExecute(r, t);
    }
  }

  @Override
  protected void terminated() {
    try {
      logger.info("Terminated: avg time={}", totalTime.get() / numTasks.get());
    } finally {
      super.terminated();
    }
  }
}
