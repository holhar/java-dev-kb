package de.holhar.java_dev_kb.training.concurrency.ch12_testing_concurrent_programs.s12_1_testing_for_correctness.s12_1_5_using_callbacks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.Test;

/**
 * Test method to verify thread pool expansion.
 */
public class ThreadPoolExpansionTest {

  private final TestingThreadFactory threadFactory = new TestingThreadFactory();

  @Test
  public void testPoolExpansion() throws InterruptedException {
    int MAX_SIZE = 10;
    ExecutorService exec = Executors.newFixedThreadPool(MAX_SIZE);

    for (int i = 0; i < 10 * MAX_SIZE; i++) {
      exec.execute(() -> {
        try {
          Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      });
    }

    for (int i = 0; i < 20 && threadFactory.numCreated.get() < MAX_SIZE; i++) {
      Thread.sleep(100);
    }

    assertEquals(threadFactory.numCreated.get(), MAX_SIZE);
    exec.shutdownNow();
  }
}
