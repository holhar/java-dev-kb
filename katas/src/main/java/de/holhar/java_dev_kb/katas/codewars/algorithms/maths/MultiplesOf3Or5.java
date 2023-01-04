package de.holhar.java_dev_kb.katas.codewars.algorithms.maths;

/**
 * Multiples of 3 or 5 (https://www.codewars.com/kata/514b92a657cdc65150000006/train/java)
 *
 * If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9.
 * The sum of these multiples is 23.
 *
 * Finish the solution so that it returns the sum of all the multiples of 3 or 5 below the number
 * passed in. Additionally, if the number is negative, return 0 (for languages that do have them).
 *
 * Note: If the number is a multiple of both 3 and 5, only count it once.
 */
public class MultiplesOf3Or5 {

  public int solution(int number) {
    if (number < 0)
      return 0;

    int result = 0;
    int i = 1;
    while (i < number) {
      if (i%3==0 || i%5==0)
        result+=i;
      i++;
    }
    return result;
  }
}
