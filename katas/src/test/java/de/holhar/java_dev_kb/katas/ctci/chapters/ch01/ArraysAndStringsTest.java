package de.holhar.java_dev_kb.katas.ctci.chapters.ch01;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArraysAndStringsTest {

    private ArraysAndStrings cut;

    @Before
    public void setup() {
        cut = new ArraysAndStrings();
    }

    @Test
    public void isUnique_true() {
        String given = "bla";
        boolean result = cut.isUnique(given);
        assertTrue(result);
    }

    @Test
    public void isUnique_withSpaces_true() {
        String given = "bla ";
        boolean result = cut.isUnique(given);
        assertTrue(result);
    }

    @Test
    public void isUnique_fail() {
        String given = "blaa";
        boolean result = cut.isUnique(given);
        assertFalse(result);
    }

    @Test
    public void isUnique_withSpaces_fail() {
        String given = "bl a ";
        boolean result = cut.isUnique(given);
        assertFalse(result);
    }

    @Test
    public void isPermutation_success() {
        String given1 = "bla";
        String given2 = "lab";
        boolean result = cut.isPermutation(given1, given2);
        assertTrue(result);
    }

    @Test
    public void isPermutation_fail() {
        String given1 = "bla";
        String given2 = "lax";
        boolean result = cut.isPermutation(given1, given2);
        assertFalse(result);
    }

    @Test
    public void urlify() {
        String value = "Mr John Smith     ";
        int length = 13;
        String result = cut.urlify(value, length);
        assertEquals("Mr%20John%20Smith", result);
    }

    @Test
    public void isPalindrome_success() {
        String value = "taco cat";
        boolean result = cut.isPalindrome(value);
        assertTrue(result);
    }

    @Test
    public void isPalindrome_fail() {
        String value = "taco caI";
        boolean result = cut.isPalindrome(value);
        assertFalse(result);
    }

    @Test
    public void isOneAway_successCase1() {
        String val1 = "pale";
        String val2 = "pake";
        boolean result = cut.isOneAway(val1, val2);
        assertTrue(result);
    }

    @Test
    public void isOneAway_successCase2() {
        String val1 = "pales";
        String val2 = "pale";
        boolean result = cut.isOneAway(val1, val2);
        assertTrue(result);
    }

    @Test
    public void isOneAway_successCase3() {
        String val1 = "pae";
        String val2 = "pale";
        boolean result = cut.isOneAway(val1, val2);
        assertTrue(result);
    }

    @Test
    public void isOneAway_failCase1() {
        String val1 = "pale";
        String val2 = "bake";
        boolean result = cut.isOneAway(val1, val2);
        assertFalse(result);
    }

    @Test
    public void isOneAway_failCase2() {
        String val1 = "pale";
        String val2 = "paeeee";
        boolean result = cut.isOneAway(val1, val2);
        assertFalse(result);
    }

    @Test
    public void compression_basicCompression() {
        String value = "aabcccccaaa";
        String result = cut.compressString(value);
        assertEquals("a2b1c5a3", result);
    }

    @Test
    public void compression_noCompression() {
        String value = "shouldNotBeCompressed";
        String result = cut.compressString(value);
        assertEquals(value, result);
    }

    @Test
    public void rotateMatrix() {
        int dimension = 4;
        int[][] given = new int[dimension][dimension];
        given[0] = new int[]{10, 11, 12, 13};
        given[1] = new int[]{14, 15, 16, 17};
        given[2] = new int[]{18, 19, 20, 21};
        given[3] = new int[]{22, 23, 24, 25};

        int[][] result = cut.rotateMatrix(given);

        int[][] expected = new int[dimension][dimension];
        expected[0] = new int[]{22, 18, 14, 10};
        expected[1] = new int[]{23, 19, 15, 11};
        expected[2] = new int[]{24, 20, 16, 12};
        expected[3] = new int[]{25, 21, 17, 13};

        for (int i = 0; i < dimension; i++) {
            assertArrayEquals(expected[i], result[i]);
        }
    }

    @Test
    public void zeroMatrix() {
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
}
