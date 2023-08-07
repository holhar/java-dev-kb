package de.holhar.java_dev_kb.katas.codewars.fundamentals.arrays;

/**
 * Multiplication table (https://www.codewars.com/kata/534d2f5b5371ecf8d2000a08/train/java)
 *
 * Your task, is to create N×N multiplication table, of size provided in parameter.
 *
 * For example, when given size is 3:
 *
 * 1 2 3
 * 2 4 6
 * 3 6 9
 *
 * For the given example, the return value should be:
 *
 * [[1,2,3],[2,4,6],[3,6,9]]
 */
public class MultiplicationTable {

  public static int[][] multiplicationTable(int n) {
    int[][] result = new int[n][n];
    int base = 1;
    int factor = 1;
    for (var i = 0; i < n; i++) {
      for (var j = 0; j < n; j++) {
        result[i][j] = base * factor;
        factor++;
      }
      base++;
      factor = 1;
    }
    return result;
  }
}
