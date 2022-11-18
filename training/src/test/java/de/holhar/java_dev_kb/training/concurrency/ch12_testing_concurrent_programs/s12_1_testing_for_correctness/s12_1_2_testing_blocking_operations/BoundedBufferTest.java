package de.holhar.java_dev_kb.training.concurrency.ch12_testing_concurrent_programs.s12_1_testing_for_correctness.s12_1_2_testing_blocking_operations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import de.holhar.java_dev_kb.training.concurrency.ch12_testing_concurrent_programs.s12_1_testing_for_correctness.BoundedBuffer;
import org.junit.jupiter.api.Test;

/**
 * Testing blocking and responsiveness to interruption.
 *
 * NOTE: The result of Thread.getState should not be used for concurrency control, and is of
 * limited usefulness for testing—its primary utility is as a source of debugging information.
 */
class BoundedBufferTest {

  private static final long LOCKUP_DETECT_TIMEOUT = 10000;

  @Test
  void takeBlockWhenEmpty() {
    BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);

    Thread taker = new Thread(() -> {
      try {
        int unused = bb.take();
        fail(); // if we get there, it's an error
      } catch (InterruptedException success) {
      }
    });

    try {
      taker.start();
      Thread.sleep(LOCKUP_DETECT_TIMEOUT);
      taker.interrupt();
      taker.join(LOCKUP_DETECT_TIMEOUT);
      assertFalse(taker.isAlive());
    } catch (Exception unexpected) {
      fail();
    }
  }
}
