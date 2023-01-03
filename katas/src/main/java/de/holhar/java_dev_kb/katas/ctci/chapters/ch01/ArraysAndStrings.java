package de.holhar.java_dev_kb.katas.ctci.chapters.ch01;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Solutions to the katas (or interview questions) for chapter 1 - Arrays and Strings.
 */
public class ArraysAndStrings {

    /**
     * Task 1.1
     * Determines if the given String has all unique characters.
     * Task condition: do not use additional data structures.
     */
    public boolean isUnique(String value) {
        final var sb = new StringBuilder();
        for (char c : value.toCharArray()) {
            if (!sb.toString().contains(String.valueOf(c))) {
                sb.append(c);
            }
        }
        return value.length() == sb.length();
    }

    /**
     * Task 1.2
     * Checks for two given strings if the second one is a permutation of the first one
     */
    public boolean isPermutation(String val1, String val2) {
        if (val1.length() != val2.length()) {
            return false;
        }
        for (char c : val2.toCharArray()) {
            if (!val1.contains(String.valueOf(c))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Task 1.3
     * URLify - replaces all spaces within a string with '%20'
     */
    public String urlify(String value, int length) {
        value = value.substring(0, length);
        return value.replace(" ", "%20");
    }

    /**
     * Task 1.4
     * Palindrome permutation - checks if a given string is a permutation of a palindrome
     */
    public boolean isPalindromePermutation(String value) {
        Map<Character, Integer> charCountMap = new HashMap<>();
        value = value.replace(" ", "");
        for (char c : value.toCharArray()) {
            if (c == ' ') {
                continue;
            }
            if (charCountMap.containsKey(c)) {
                charCountMap.put(c, charCountMap.get(c) + 1);
            } else {
                charCountMap.put(c, 1);
            }
        }

        if (value.length() % 2 != 0) {
            Character middleChar = charCountMap.entrySet().stream()
                    .filter(e -> e.getValue() == 1)
                    .map(Map.Entry::getKey)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("This shouldn't happen"));
            charCountMap.remove(middleChar);
        }

        for (Integer charCount : charCountMap.values()) {
            if (charCount%2 != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Task 1.5
     * One Away - determines if two given strings are maximum one string operation apart, i.e. replace, remove or insert
     * one character
     */
    public boolean isOneAway(String val1, String val2) {
        for (char c : val1.toCharArray()) {
            if (val2.contains(String.valueOf(c))) {
                val2 = val2.replaceFirst(String.valueOf(c), "");
            }
        }
        return val2.length() < 2;
    }

    /**
     * Task 1.6
     * Apply basic string compression using the count of the characters
     */
    public String compressString(String value) {
        final var sb = new StringBuilder(value.length());
        final char[] chars = value.toCharArray();
        var lastChar = chars[0];
        var letterCounter = 0;
        for (char c : chars) {
            if (c == lastChar) {
                letterCounter++;
            } else {
                sb.append(lastChar);
                if (letterCounter > 0) {
                    sb.append(letterCounter);
                }
                lastChar = c;
                letterCounter = 1;
            }
        }
        sb.append(lastChar);
        if (letterCounter > 0) {
            sb.append(letterCounter);
        }
        return value.length() < sb.toString().length() ? value : sb.toString();
    }

    /**
     * Task 1.7
     * Given an image represented by an NxN matrix, where each pixel in the image is 4 bytes, write a method to
     * rotate the image by 90 degrees. can you do this in place?
     */
    public int[][] rotateMatrix(int[][] matrix) {
        var n = matrix.length;
        var result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[j][n - 1 - i] = matrix[i][j];
            }
        }
        return result;
    }

    // FIXME: Not working for n > 5
    public int[][] rotateMatrixInPlace(int[][] matrix) {
        var n = matrix.length - 1;
        for (var i = 0; i < (n + 1) / 2; i++) {
            for (var j = 0; j < n; j++) {
                if (n > 1 && (i == (n+1)/2 - 1) && (j == n/2)) {
                    break;
                }
                var start = matrix[i][j + i];
                matrix[i][j + i] = matrix[n - j - i][i];
                matrix[n - j - i][i] = matrix[n - i][n - j - i];
                matrix[n - i][n - j - i] = matrix[j + i][n - i];
                matrix[j + i][n - i] = start;
            }
        }
        return matrix;
    }

    /**
     * Task 1.8
     * Write an algorithm such that if an element in an MxN matrix is 0, its entire row and column are set to 0.
     */
    public int[][] zeroMatrix(int[][] matrix) {
        Set<Integer> nullCols = new HashSet<>();
        Set<Integer> nullRows = new HashSet<>();
        for (var i = 0; i < matrix.length; i++) {
            for (var j = 0; j < matrix[0].length; j++) {
                var cellVal = matrix[i][j];
                if (cellVal == 0) {
                    nullRows.add(i);
                    nullCols.add(j);
                }
            }
        }
        for (var i = 0; i < matrix.length; i++) {
            for(var j = 0; j < matrix[0].length; j++) {
                if (nullRows.contains(i) || nullCols.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
        return matrix;
    }
}
