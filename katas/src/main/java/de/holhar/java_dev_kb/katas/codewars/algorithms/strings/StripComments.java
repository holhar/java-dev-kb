package de.holhar.java_dev_kb.katas.codewars.algorithms.strings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

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
 * TODO finish me!
 */
public class StripComments {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(StripComments.class);
    
    private StripComments() {}

    public static String stripComments(String text, String[] commentSymbols) {
        String symbols = Arrays.stream(commentSymbols).reduce("", (s1, s2) -> s1 + "\\" + s2);
        LOGGER.debug("symbols: '{}', text: '{}'", symbols, text);
        String result = text.replaceAll("\\s?[" + symbols + "].*?|\\s?$", "");
        LOGGER.debug("result: '{}'", result);
        return result;
    }
}
