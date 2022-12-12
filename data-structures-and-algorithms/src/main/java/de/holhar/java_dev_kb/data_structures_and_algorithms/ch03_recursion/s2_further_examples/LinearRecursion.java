package de.holhar.java_dev_kb.data_structures_and_algorithms.ch03_recursion.s2_further_examples;

/**
 * If a recursive method is designed so that each invocation of the body makes at most one new
 * recursive call, this is known as linear recursion. This reflects the structure of the
 * recursion trace, not the asymptotic analysis of the running time
 */
public class LinearRecursion {

  /**
   * Returns the sum of the first n integers of the given array.
   *
   * Runs in O(n) time.
   */
  public static int linearSum(int[] data, int n) {
    if (n == 0)
      return 0;
    else
      return linearSum(data, n - 1) + data[n - 1];
  }

  /**
   * Reverses the contents of subarray data[low] through data[high] inclusive.
   *
   * Runs in O(n) time.
   */
  public static void reverseArray(int[] data, int low, int high) {
    if (low < high) {
      int temp = data[low];
      data[low] = data[high];
      data[high] = temp;
      reverseArray(data, low + 1, high - 1);
    }
  }

  /**
   * Computes the value of x raised to the nth power, for non-negative integer n.
   *
   * Runs in O(n) time.
   */
  public static double power1(double x, int n) {
    if (n == 0)
      return 1;
    else
      return x * power1(x, n - 1);
  }

  /**
   * Computes the value of x raised to the nth power, for non-negative integer n.
   *
   * Runs in O(log n) time.
   */
  public static double power2(double x, int n) {
    if (n == 0)
      return 1;
    else {
      double partial = power2(x, n - 1);
      double result = partial * partial;
      if (n % 2 == 1)
        result *= x;
      return result;
    }
  }
}
