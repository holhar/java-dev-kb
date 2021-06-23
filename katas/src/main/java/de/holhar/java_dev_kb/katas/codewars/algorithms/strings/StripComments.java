package de.holhar.java_dev_kb.katas.codewars.algorithms.strings;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Strip Comments (https://www.codewars.com/kata/51c8e37cee245da6b40000bd)
 * <p>
 * Complete the solution so that it strips all text that follows any of a set of comment markers passed in. Any
 * whitespace at the end of the line should also be stripped out.
 * <p>
 * Example:
 * <p>
 * Given an input string of:
 * <p>
 * apples, pears # and bananas
 * grapes
 * bananas !apples
 * <p>
 * The output expected would be:
 * <p>
 * apples, pears
 * grapes
 * bananas
 * <p>
 * The code would be called like so:
 * <p>
 * var result = solution("apples, pears # and bananas\ngrapes\nbananas !apples", ["#", "!"])
 * // result should == "apples, pears\ngrapes\nbananas"
 * <p>
 */
public class StripComments {
    
    private StripComments() {}

    public static String stripComments(String text, String[] commentSymbols) {
        return Arrays.stream(text.split("\\n"))
                .map(line -> stripComment(line, commentSymbols))
                .collect(Collectors.joining("\n"));
    }

    private static String stripComment(String line, String[] commentSymbols) {
        var matches = false;
        var symbol = "";
        for (String s : commentSymbols) {
            if (line.contains(s)) {
                matches = true;
                symbol = s;
                break;
            }
        }
        if (matches) {
            return line.substring(0, line.indexOf(symbol)).trim();
        } else {
            return line.isBlank() ? "" : line.replaceAll("(\\w)\\s+$", "$1");
        }
    }
}
