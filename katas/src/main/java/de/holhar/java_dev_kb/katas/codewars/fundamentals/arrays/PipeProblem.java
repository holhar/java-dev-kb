package de.holhar.java_dev_kb.katas.codewars.fundamentals.arrays;

import java.util.stream.IntStream;

/**
 * Lario and Muigi Pipe Problem (https://www.codewars.com/kata/56b29582461215098d00000f/train/java)
 *
 * Issue
 *
 * Looks like some hoodlum plumber and his brother has been running around and damaging your stages again.
 *
 * The pipes connecting your level's stages together need to be fixed before you receive any more complaints.
 *
 * The pipes are correct when each pipe after the first is 1 more than the previous one.
 * Task
 *
 * Given the a list of unique numbers sorted in ascending order, return a new list so that the values increment by 1 for each index from the minimum value up to the maximum value (both included).
 * Example
 *
 * Input:  1,3,5,6,7,8 Output: 1,2,3,4,5,6,7,8
 */
public class PipeProblem {

  public static int[] pipeFix(int[] numbers) {
    return IntStream.range(numbers[0], numbers[numbers.length - 1] + 1).toArray();
  }
}
