package de.holhar.java_dev_kb.training.concurrency.ch12_testing_concurrent_programs.s12_1_testing_for_correctness.s12_1_1_basic_unit_tests;

import static org.junit.jupiter.api.Assertions.*;

import de.holhar.java_dev_kb.training.concurrency.ch12_testing_concurrent_programs.s12_1_testing_for_correctness.BoundedBuffer;
import org.junit.jupiter.api.Test;

/**
 * Basic unit tests for BoundedBuffer.
 */
class BoundedBufferTest {

  @Test
  void isEmptyWhenConstructed() {
    BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);

    assertTrue(bb.isEmpty());
    assertFalse(bb.isFull());
  }

  @Test
  void isFullAfterPuts() throws InterruptedException {
    BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);

    for (int i = 0; i < 10; i++) {
      bb.put(i);
    }

    assertTrue(bb.isFull());
    assertFalse(bb.isEmpty());
  }
}
