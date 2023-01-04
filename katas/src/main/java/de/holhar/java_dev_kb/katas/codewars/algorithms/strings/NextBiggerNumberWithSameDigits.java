package de.holhar.java_dev_kb.katas.codewars.algorithms.strings;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Next bigger number with the same digits (https://www.codewars.com/kata/55983863da40caa2c900004e/train/java)
 *
 * Create a function that takes a positive integer and returns the next bigger number that can be
 * formed by rearranging its digits.
 *
 * If the digits can't be rearranged to form a bigger number, return -1 (or nil in Swift):
 */
public class NextBiggerNumberWithSameDigits {

  // Not fully functional
  public static long nextBiggerNumberAlternative(long n) {
    char[] chars = String.valueOf(n).toCharArray();
    long result = Long.parseLong(new String(chars));
    int index = chars.length - 1;

    while (index > 0 && result <= n) {
      char temp = chars[index];
      chars[index] = chars[index-1];
      chars[index-1] = temp;
      result = Long.parseLong(new String(chars));
      index--;
    }

    if (result <= n) {
      return -1;
    }
    return result;
  }

  public static long nextBiggerNumber(long n) {
    String numberString = String.valueOf(n);
    List<Long> numbers = new ArrayList<>();
    permutation(numberString, n, numbers);
    numbers.sort(Comparator.comparingLong(o -> o));
    if (numbers.size() == 0)
      return -1;
    return numbers.get(0);
  }

  private static void permutation(String str, long n, List<Long> numbers) {
    permutation("", str, n, numbers);
  }

  private static void permutation(String prefix, String str, long n, List<Long> numbers) {
    int size = str.length();
    if (size == 0) {
      long newValue = Long.parseLong(prefix);
      if (newValue > n)
        numbers.add(newValue);
    }
    else {
      for (int i = 0; i < size; i++)
        permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, size), n, numbers);
    }
  }
}
