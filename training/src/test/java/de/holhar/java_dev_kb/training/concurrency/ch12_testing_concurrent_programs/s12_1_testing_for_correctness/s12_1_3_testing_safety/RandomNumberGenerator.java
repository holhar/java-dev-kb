package de.holhar.java_dev_kb.training.concurrency.ch12_testing_concurrent_programs.s12_1_testing_for_correctness.s12_1_3_testing_safety;

/**
 * Medium-quality random number generator suitable for testing.
 */
public class RandomNumberGenerator {

  public static int xorShift(int y) {
    y ^= (y << 6);
    y ^= (y >>> 21);
    y ^= (y << 7);
    return y;
  }
}
