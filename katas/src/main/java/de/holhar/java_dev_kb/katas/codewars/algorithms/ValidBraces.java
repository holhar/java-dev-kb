package de.holhar.java_dev_kb.katas.codewars.algorithms;


import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Valid Braces (https://www.codewars.com/kata/5277c8a221e209d3f6000b56)
 *
 * Write a function that takes a string of braces, and determines if the order of the braces is valid. It should
 * return true if the string is valid, and false if it's invalid.
 *
 * This Kata is similar to the Valid Parentheses Kata, but introduces new characters: brackets [], and curly braces {}.
 * Thanks to @arnedag for the idea!
 *
 * All input strings will be nonempty, and will only consist of parentheses, brackets and curly braces: ()[]{}.
 * What is considered Valid?
 *
 * A string of braces is considered valid if all braces are matched with the correct brace.
 *
 * Examples
 *
 * "(){}[]"   =>  True
 * "([{}])"   =>  True
 * "(}"       =>  False
 * "[(])"     =>  False
 * "[({})](]" =>  False
 */
public class ValidBraces {

    public boolean isValid(String braces) {
        return !(containsMixedHierarchies(braces)
                || hasMissingBraces(braces)
                || hasOddCount(braces));
    }

    private boolean containsMixedHierarchies(String braces) {
        return braces.contains("(]")
                || braces.contains("(}")
                || braces.contains("[)")
                || braces.contains("[}")
                || braces.contains("{)")
                || braces.contains("{]");
    }

    private boolean hasMissingBraces(String braces) {
        return braces.matches("[^{]*}.*?\\{[^}]*")
                || braces.matches("[^\\[]*].*?\\[[^]]*")
                || braces.matches("[^(]*\\).*?\\([^)]*");
    }

    private boolean hasOddCount(String braces) {
        Map<Character, Integer> bracesCountMap = new HashMap<>();
        bracesCountMap.put('{', 0);
        bracesCountMap.put('}', 0);
        bracesCountMap.put('(', 0);
        bracesCountMap.put(')', 0);
        bracesCountMap.put('[', 0);
        bracesCountMap.put(']', 0);

        braces.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList())
                .forEach(c -> bracesCountMap.put(c, bracesCountMap.get(c) + 1));

        return !bracesCountMap.get('{').equals(bracesCountMap.get('}'))
                || !bracesCountMap.get('[').equals(bracesCountMap.get(']'))
                || !bracesCountMap.get('(').equals(bracesCountMap.get(')'));
    }
}