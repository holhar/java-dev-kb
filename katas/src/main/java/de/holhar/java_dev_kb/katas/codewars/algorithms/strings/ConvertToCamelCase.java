package de.holhar.java_dev_kb.katas.codewars.algorithms.strings;

import java.util.stream.IntStream;

/**
 * Convert string to camel case (https://www.codewars.com/kata/517abf86da9663f1d2000003)
 * <p>
 * Complete the method/function so that it converts dash/underscore delimited words into camel casing. The first word
 * within the output should be capitalized only if the original word was capitalized (known as Upper Camel Case, also
 * often referred to as Pascal case).
 * <p>
 * Examples
 * <p>
 * toCamelCase("the-stealth-warrior"); // returns "theStealthWarrior"
 * <p>
 * toCamelCase("The_Stealth_Warrior"); // returns "TheStealthWarrior"
 */
public class ConvertToCamelCase {

    private ConvertToCamelCase() {}

    static String toCamelCase(String input) {
        String[] words = input.split("([-_])");
        StringBuilder result = new StringBuilder(input.length());
        IntStream.range(0, words.length).forEach(
                i -> {
                    if (i == 0) {
                        result.append(words[i]);
                    } else {
                        result.append(words[i].substring(0, 1).toUpperCase());
                        result.append(words[i].substring(1));
                    }
                }
        );
        return result.toString();
    }
}
