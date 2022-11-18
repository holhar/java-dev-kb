package de.holhar.java_dev_kb.training.concurrency.ch12_testing_concurrent_programs.s12_1_testing_for_correctness.s12_1_4_testing_resource_mgmt;

import static org.junit.jupiter.api.Assertions.assertTrue;

import de.holhar.java_dev_kb.training.concurrency.ch12_testing_concurrent_programs.s12_1_testing_for_correctness.BoundedBuffer;
import org.junit.jupiter.api.Test;

/**
 * Testing for resource leaks.
 */
public class ResourceLeaksTest {

  private static final int CAPACITY = 100_000;
  private static final int THRESHOLD = -1;

  class Big {
    double[] data = new double[100_000];
  }

  @Test
  void testLeak() throws InterruptedException {
    BoundedBuffer<Big> bb = new BoundedBuffer<>(CAPACITY);
    int heapSize1 = -1; /* snapshot heap */
    for (int i = 0; i < CAPACITY; i++) {
      bb.put(new Big());
    }
    for (int i = 0; i < CAPACITY; i++) {
      bb.take();
    }
    int heapSize2 = -1; /* snapshot heap */
    assertTrue(Math.abs(heapSize1 - heapSize2) < THRESHOLD);
  }
}
