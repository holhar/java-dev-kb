package de.holhar.java_dev_kb.katas.codewars.algorithms;

import java.util.Arrays;

/**
 * Range Extraction (https://www.codewars.com/kata/51ba717bb08c1cd60f00002f/train/java)
 *
 * A format for expressing an ordered list of integers is to use a comma separated list of either:
 * - individual integers
 * - or a range of integers denoted by the starting integer separated from the end integer in the
 * range by a dash, '-'. The range includes all integers in the interval including both endpoints.
 * It is not considered a range unless it spans at least 3 numbers. For example "12,13,15-17"
 *
 * Complete the solution so that it takes a list of integers in increasing order and returns a
 * correctly formatted string in the range format.
 *
 * Example:
 *
 * Solution.rangeExtraction(new int[] {-10, -9, -8, -6, -3, -2, -1, 0, 1, 3, 4, 5, 7, 8, 9, 10, 11, 14, 15, 17, 18, 19, 20})
 * # returns "-10--8,-6,-3-1,3-5,7-11,14,15,17-20"
 *
 * Courtesy of rosettacode.org
 */
public class RangeExtraction {

  public static String rangeExtraction(int[] arr) {
    Arrays.sort(arr);
    StringBuilder result = new StringBuilder();

    int current;
    int predecessor = 0;
    int successor = 0;
    boolean inRange = false;

    for (int i = 0; i < arr.length; i++) {
      current = arr[i];
      if (i == 0) {
        result.append(current);
      }
      else if (i == arr.length - 1) {
        if (inRange) {
          result.append(current);
        } else {
          result.append(",").append(current);
        }
      } else if (current - 1 == predecessor && current + 1 == successor) {
        if (!result.toString().endsWith("-")) {
          result.append("-");
          inRange = true;
        }
      } else if (inRange && current - 1 == predecessor && current + 1 != successor) {
        result.append(current).append(",");
      } else {
        if (inRange) {
          result.append(current);
          inRange = false;
        } else {
          result.append(",").append(current);
        }
      }

      predecessor = current;
      if (i < arr.length - 2)
        successor = arr[i+2];
    }
    return result.toString();
  }
}
