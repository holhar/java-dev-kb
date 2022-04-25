package de.holhar.java_dev_kb.katas.codewars.algorithms.strings;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Simple Pig Latin - https://www.codewars.com/kata/520b9d2ad5c005041100000f/train/java
 *
 * Move the first letter of each word to the end of it, then add "ay" to the end of the word. Leave punctuation marks untouched.
 * Examples
 *
 * pigIt('Pig latin is cool'); // igPay atinlay siay oolcay
 * pigIt('Hello world !');     // elloHay orldway !
 */
public class SimplePigLatin {

  private static final String PIG_LATIN_SUFFIX = "ay";
  private static final Pattern pattern = Pattern.compile("[a-zA-Z]*");

  private SimplePigLatin() {
    throw new UnsupportedOperationException("Static helper class");
  }

  public static String pigIt(String str) {
    return Arrays.stream(str.split(" "))
        .map(SimplePigLatin::mapWord)
        .collect(Collectors.joining(" "));
  }

  private static String mapWord(String word) {
    if (pattern.matcher(word).matches()) {
      return word.substring(1) + word.charAt(0) + PIG_LATIN_SUFFIX;
    } else {
      return word;
    }
  }
}
