package de.holhar.java_dev_kb.data_structures_and_algorithms.ch03_recursion.s1_illustrative_examples;

public class FactorialFunction {

  public static int factorial(int n) throws IllegalArgumentException {
    if (n < 0) {
      throw new IllegalArgumentException();
    } else if (n == 0) {
      return 1;
    } else {
      return n * factorial(n - 1);
    }
  }
}
