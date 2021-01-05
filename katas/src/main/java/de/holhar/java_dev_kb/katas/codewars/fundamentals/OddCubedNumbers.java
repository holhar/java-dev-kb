package de.holhar.java_dev_kb.katas.codewars.fundamentals;

import java.util.Arrays;

/**
 * Sum of Odd Cubed Numbers (https://www.codewars.com/kata/580dda86c40fa6c45f00028a)
 * <p>
 * Find the sum of the odd numbers within an array, after cubing the initial integers. The function should return
 * undefined/None/nil/NULL if any of the values aren't numbers.
 * <p>
 * Note: There are ONLY integers in the JAVA and C# versions of this Kata.
 */
public class OddCubedNumbers {

    private OddCubedNumbers() {}

    public static int cubeOdd(int[] arr) {
        return Arrays.stream(arr).filter(n -> n % 2 != 0).map(n -> n * n * n).sum();
    }
}
