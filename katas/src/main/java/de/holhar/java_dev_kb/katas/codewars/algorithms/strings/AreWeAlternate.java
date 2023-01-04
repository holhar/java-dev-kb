package de.holhar.java_dev_kb.katas.codewars.algorithms.strings;

import java.util.Arrays;
import java.util.List;

/**
 * Are we alternate? (https://www.codewars.com/kata/59325dc15dbb44b2440000af/train/java)
 *
 * Create a function isAlt() that accepts a string as an argument and validates whether the
 * vowels (a, e, i, o, u) and consonants are in alternate order.
 *
 * isAlt("amazon")
 * // true
 * isAlt("apple")
 * // false
 * isAlt("banana")
 * // true
 *
 * Arguments consist of only lowercase letters.
 */
public class AreWeAlternate {

  public static boolean isAlt(String word) {
    List<Character> vowels = Arrays.asList('a', 'e', 'i', 'o', 'u');
    char[] chars = word.toCharArray();
    boolean startsWithVowel = vowels.contains(chars[0]);
    boolean result = true;
    for (int i = 0; i < chars.length; i++) {
      if ((i+1)%2!=0) {
        if (startsWithVowel && !vowels.contains(chars[i])) {
          result = false;
          break;
        } else if (!startsWithVowel && vowels.contains(chars[i])) {
          result = false;
          break;
        }
      } else if ((i+1)%2==0) {
        if (startsWithVowel && vowels.contains(chars[i])) {
          result = false;
          break;
        } else if (!startsWithVowel && !vowels.contains(chars[i])) {
          result = false;
          break;
        }
      }
    }
    return result;
  }
}
