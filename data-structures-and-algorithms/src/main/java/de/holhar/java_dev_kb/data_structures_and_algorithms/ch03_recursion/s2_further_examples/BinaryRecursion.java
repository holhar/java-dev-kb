package de.holhar.java_dev_kb.data_structures_and_algorithms.ch03_recursion.s2_further_examples;

/**
 * When a method makes two recursive calls, we say that it uses binary recursion.
 */
public class BinaryRecursion {

  /**
   * Returns the sum of subarray data[low] through data[high] inclusive.
   *
   * Runs in O(n) time, but but uses O(log n) space (compared to linearSum method.
   */
  public static int binarySum(int[] data, int low, int high) {
    // zero elements in subarray
    if (low > high)
      return 0;
    // one element in subarray
    else if (low == high)
      return data[low];
    else {
      int mid = (low + high) / 2;
      return binarySum(data, low, mid) + binarySum(data, mid + 1, high);
    }
  }
}
