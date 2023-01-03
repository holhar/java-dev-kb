package de.holhar.java_dev_kb.katas.ctci.chapters.ch01;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArraysAndStringsTest {

    private ArraysAndStrings cut;

    @BeforeEach
    public void setup() {
        cut = new ArraysAndStrings();
    }

    @Test
    public void isUnique_true() {
    }

    @Test
    public void isUnique_withSpaces_true() {
    }

    @Test
    public void isUnique_fail() {
    }

    @Test
    public void isUnique_withSpaces_fail() {
    }

    @Test
    public void isPermutation_success() {
    }

    @Test
    public void isPermutation_fail() {
    }

    @Test
    public void urlify() {
    }

    @Test
    public void isPalindromePermutation_success() {
        String value = "taco cat";
        boolean result = cut.isPalindromePermutation(value);
        assertTrue(result);
    }

    @Test
    public void isPalindromePermutation_fail() {
        String value = "taco caI";
        boolean result = cut.isPalindromePermutation(value);
        assertFalse(result);
    }

    @Test
    public void isOneAway_successCase1() {
    }

    @Test
    public void isOneAway_successCase2() {
    }

    @Test
    public void isOneAway_successCase3() {
    }

    @Test
    public void isOneAway_failCase1() {
    }

    @Test
    public void isOneAway_failCase2() {
    }

    @Test
    public void compression_basicCompression() {
    }

    @Test
    public void compression_noCompression() {
        String value = "shouldNotBeCompresed";
        String result = cut.compressString(value);
        assertEquals(value, result);
    }

    @Test
    public void rotate2x2Matrix() {
        int dimension = 2;
        int[][] given = new int[dimension][dimension];
        given[0] = new int[]{1, 2};
        given[1] = new int[]{3, 4};

        int[][] result = cut.rotateMatrix(given);

        int[][] expected = new int[dimension][dimension];
        expected[0] = new int[]{3, 1};
        expected[1] = new int[]{4, 2};

        for (int i = 0; i < dimension; i++) {
            assertArrayEquals(expected[i], result[i]);
        }
    }

    @Test
    public void rotate4x4Matrix() {
        int dimension = 4;
        int[][] given = new int[dimension][dimension];
        given[0] = new int[]{1, 2, 3, 4};
        given[1] = new int[]{5, 6, 7, 8};
        given[2] = new int[]{9, 10, 11, 12};
        given[3] = new int[]{13, 14, 15, 16};

        int[][] result = cut.rotateMatrix(given);

        int[][] expected = new int[dimension][dimension];
        expected[0] = new int[]{13, 9, 5, 1};
        expected[1] = new int[]{14, 10, 6, 2};
        expected[2] = new int[]{15, 11, 7, 3};
        expected[3] = new int[]{16, 12, 8, 4};

        for (int i = 0; i < dimension; i++) {
            assertArrayEquals(expected[i], result[i]);
        }
    }

    @Test
    public void rotate5x5Matrix() {
        int dimension = 5;
        int[][] given = new int[dimension][dimension];
        given[0] = new int[]{1, 2, 3, 4, 5};
        given[1] = new int[]{6, 7, 8, 9, 10};
        given[2] = new int[]{11, 12, 13, 14, 15};
        given[3] = new int[]{16, 17, 18, 19, 20};
        given[4] = new int[]{21, 22, 23, 24, 25};

        int[][] result = cut.rotateMatrix(given);

        int[][] expected = new int[dimension][dimension];
        expected[0] = new int[]{21, 16, 11, 6, 1};
        expected[1] = new int[]{22, 17, 12, 7, 2};
        expected[2] = new int[]{23, 18, 13, 8, 3};
        expected[3] = new int[]{24, 19, 14, 9, 4};
        expected[4] = new int[]{25, 20, 15, 10, 5};

        for (int i = 0; i < dimension; i++) {
            assertArrayEquals(expected[i], result[i]);
        }
    }

    @Test
    public void rotate7x7Matrix() {
        int dimension = 7;
        int[][] given = new int[dimension][dimension];
        given[0] = new int[]{1, 2, 3, 4, 5, 6, 7};
        given[1] = new int[]{8, 9, 10, 11, 12, 13, 14};
        given[2] = new int[]{15, 16, 17, 18, 19, 20, 21};
        given[3] = new int[]{22, 23, 24, 25, 26, 27, 28};
        given[4] = new int[]{29, 30, 31, 32, 33, 34, 35};
        given[5] = new int[]{36, 37, 38, 39, 40, 41, 42};
        given[6] = new int[]{43, 44, 45, 46, 47, 48, 49};

        int[][] result = cut.rotateMatrix(given);

        int[][] expected = new int[dimension][dimension];
        expected[0] = new int[]{43, 36, 29, 22, 15, 8, 1};
        expected[1] = new int[]{44, 37, 30, 23, 16, 9, 2};
        expected[2] = new int[]{45, 38, 31, 24, 17, 10, 3};
        expected[3] = new int[]{46, 39, 32, 25, 18, 11, 4};
        expected[4] = new int[]{47, 40, 33, 26, 19, 12, 5};
        expected[5] = new int[]{48, 41, 34, 27, 20, 13, 6};
        expected[6] = new int[]{49, 42, 35, 28, 21, 14, 7};

        for (int i = 0; i < dimension; i++) {
            assertArrayEquals(expected[i], result[i]);
        }
    }

    @Test
    public void zero4x4Matrix() {
        int n = 4, m = 3;
        int[][] given = new int[n][m];
        given[0] = new int[]{10, 11, 12, 13};
        given[1] = new int[]{14, 0, 16, 17};
        given[2] = new int[]{18, 19, 20, 21};
        given[3] = new int[]{22, 23, 24, 25};

        int[][] result = cut.zeroMatrix(given);

        int[][] expected = new int[n][m];
        expected[0] = new int[]{10, 0, 12, 13};
        expected[1] = new int[]{0, 0, 0, 0};
        expected[2] = new int[]{18, 0, 20, 21};
        expected[3] = new int[]{22, 0, 24, 25};

        for (int i = 0; i < n; i++) {
            assertArrayEquals(expected[i], result[i]);
        }
    }

    @Test
    public void zero7x6Matrix() {
        int n = 7, m = 6;
        int[][] given = new int[n][m];
        given[0] = new int[]{1, 2, 3, 4, 5, 6};
        given[1] = new int[]{8, 9, 0, 11, 12, 13};
        given[2] = new int[]{15, 16, 17, 18, 19, 20};
        given[3] = new int[]{22, 23, 24, 0, 26, 27};
        given[4] = new int[]{29, 30, 31, 32, 33, 34};
        given[5] = new int[]{36, 37, 38, 39, 40, 0};
        given[6] = new int[]{43, 44, 45, 46, 47, 48};

        int[][] result = cut.zeroMatrix(given);

        int[][] expected = new int[n][m];
        expected[0] = new int[]{1, 2, 0, 0, 5, 0};
        expected[1] = new int[]{0, 0, 0, 0, 0, 0};
        expected[2] = new int[]{15, 16, 0, 0, 19, 0};
        expected[3] = new int[]{0, 0, 0, 0, 0, 0};
        expected[4] = new int[]{29, 30, 0, 0, 33, 0};
        expected[5] = new int[]{0, 0, 0, 0, 0, 0};
        expected[6] = new int[]{43, 44, 0, 0, 47, 0};

        for (int i = 0; i < n; i++) {
            assertArrayEquals(expected[i], result[i]);
        }
    }
}
