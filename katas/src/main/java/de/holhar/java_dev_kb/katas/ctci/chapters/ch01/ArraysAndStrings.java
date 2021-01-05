package de.holhar.java_dev_kb.katas.ctci.chapters.ch01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Solutions to the katas (or interview questions) for chapter 1 - Arrays and Strings.
 */
public class ArraysAndStrings {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArraysAndStrings.class);

    /**
     * Task 1.1
     * Determines if the given String has all unique characters.
     * Task condition: do not use additional data structures.
     */
    public boolean isUnique(String value) {
        Objects.requireNonNull(value, "Provided string value must not be null");

        StringBuilder uniqueChars = new StringBuilder();
        for (String charVal : value.split("")) {
            if (!uniqueChars.toString().contains(charVal)) {
                uniqueChars.append(charVal);
            }
        }
        return uniqueChars.toString().length() == value.length();
    }

    /**
     * Task 1.2
     * Checks for two given strings if the second one is a permutation of the first one
     */
    public boolean isPermutation(String val1, String val2) {
        Objects.requireNonNull(val1, "Provided string 'val1' must not be null");
        Objects.requireNonNull(val2, "Provided string 'val2' must not be null");

        if (val1.length() != val2.length()) {
            return false;
        }

        int count = 0;
        ROW_LOOP:
        for (int row = 1; row <= 3; row++)
            for (int col = 1; col <= 2; col++) {
                if (row * col % 2 == 0) continue ROW_LOOP;
                count++;
            }
        LOGGER.debug("{}", count);

        List<Character> val1Chars = val1.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        List<Character> val2Chars = val2.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        val1Chars.sort(Comparator.comparing(Character::charValue));
        val2Chars.sort(Comparator.comparing(Character::charValue));
        return val1Chars.equals(val2Chars);
    }

    /**
     * Task 1.3
     * URLify - replaces all spaces within a string with '%20'
     */
    public String urlify(String value, int length) {
        Objects.requireNonNull(value, "Provided string must not be null");
        if (length < 0) {
            throw new IllegalArgumentException("The provided string length must not be smaller than zero");
        }
        String result = value.trim();
        return result.replaceAll("\\s", "%20");
    }

    /**
     * Task 1.4
     * Palindrome permutation - checks if a given string is a palindrome
     */
    public boolean isPalindrome(String value) {
        Objects.requireNonNull(value, "Provided string must not be null");

        value = value.trim();
        value = value.replaceAll("\\s", "");

        char[] chars = value.toCharArray();
        StringBuilder valueReverse = new StringBuilder();
        for (int i = chars.length - 1; i >= 0; i--) {
            valueReverse.append(chars[i]);
        }
        return value.equals(valueReverse.toString());
    }

    /**
     * Task 1.5
     * One Away - determines if two given strings are maximum one string operation apart, i.e. replace, remove or insert
     * one character
     */
    public boolean isOneAway(String val1, String val2) {
        List<Character> charList1 = getCharactersAsList(val1);
        List<Character> charList2 = getCharactersAsList(val2);

        List<Character> charList1Copy = new ArrayList<>(charList1);
        charList1Copy.removeAll(charList2);
        List<Character> charList2Copy = new ArrayList<>(charList2);
        charList2Copy.removeAll(charList1);

        if (Math.abs(charList1.size() - charList2.size()) > 1) {
            return false;
        } else if (charList1.size() == charList2.size()) {
            return isOneAwayEqualLength(val1, val2, String.valueOf(charList1Copy.get(0)), String.valueOf(charList2Copy.get(0)));
        } else if (charList1Copy.size() == 1 || charList2Copy.size() == 1) {
            return isOneAwayUnequalLength(val1, val2, charList1, charList2);
        }
        return false;
    }

    /**
     * Task 1.6
     * Apply basic string compression using the count of the characters
     */
    public String compressString(String value) {
        List<Character> charList = getCharactersAsList(value);
        StringBuilder result = new StringBuilder();
        String previousLetter = "";
        String currentLetter;
        int counter = 1;
        for (Character letter : charList) {
            currentLetter = String.valueOf(letter);
            if (!previousLetter.isEmpty()) {
                if (currentLetter.equals(previousLetter)) {
                    counter++;
                } else {
                    result.append(previousLetter);
                    result.append(counter);
                    counter = 1;
                }
            }
            previousLetter = currentLetter;
        }
        result.append(previousLetter);
        result.append(counter);
        return value.length() < result.toString().length() ? value : result.toString();
    }

    /**
     * Task 1.7
     * Given an image represented by an NxN matrix, where each pixel in the image is 4 bytes, write a method to
     * rotate the image by 90 degrees. can you do this in place?
     */
    public int[][] rotateMatrix(int[][] matrix) {
        if (matrix.length != matrix[0].length) {
            throw new IllegalArgumentException("The input matrix needs to be symmetric!");
        }
        int dimension = matrix.length;
        int[][] result = new int[dimension][dimension];

        int columnIndex = dimension - 1;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                result[j][columnIndex] = matrix[i][j];
            }
            columnIndex--;
        }
        return result;
    }

    /**
     * Task 1.8
     * Write an algorithm such that if an element in an MxN matrix is 0, its entire row and column are set to 0.
     */
    public int[][] zeroMatrix(int[][] matrix) {
        int n = matrix[0].length;
        int m = matrix.length;

        int zeroXCoordinate = -1;
        int zeroYCoordinate = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0) {
                    zeroXCoordinate = j;
                    zeroYCoordinate = i;
                    break;
                }
            }
            if (zeroXCoordinate != -1) {
                break;
            }
        }

        if (zeroXCoordinate == -1) {
            return matrix;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == zeroYCoordinate || j == zeroXCoordinate) {
                    matrix[i][j] = 0;
                }
            }
        }
        return matrix;
    }

    private List<Character> getCharactersAsList(String value) {
        char[] charArr = value.toCharArray();
        ArrayList<Character> charList = new ArrayList<>();
        for (int i = 0; i < charArr.length; i++) {
            charList.add(charArr[i]);
        }
        return charList;
    }

    private boolean isOneAwayEqualLength(String val1, String val2, String toBeRemovedFromVal1, String toBeRemovedFromVal2) {
        val1 = val1.replace(toBeRemovedFromVal1, "");
        val2 = val2.replace(toBeRemovedFromVal2, "");
        return val1.equals(val2);
    }

    private boolean isOneAwayUnequalLength(String val1, String val2, List<Character> charList1, List<Character> charList2) {
        if (charList1.size() > charList2.size()) {
            return sanitizeAndCompareStrings(charList1, charList2, val1, val2);
        } else {
            return sanitizeAndCompareStrings(charList2, charList1, val2, val1);
        }
    }

    private boolean sanitizeAndCompareStrings(List<Character> list1, List<Character> list2, String val1, String val2) {
        list1.removeAll(list2);
        String toBeRemoved = String.valueOf(list1.get(0));
        val1 = val1.replace(toBeRemoved, "");
        return val1.equals(val2);
    }
}
