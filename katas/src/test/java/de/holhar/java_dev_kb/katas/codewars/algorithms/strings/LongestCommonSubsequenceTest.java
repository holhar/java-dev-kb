package de.holhar.java_dev_kb.katas.codewars.algorithms.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LongestCommonSubsequenceTest {

    @Test
    void exampleTests() {
        assertEquals("", LongestCommonSubsequence.lcs("a", "b"));
        assertEquals("abc", LongestCommonSubsequence.lcs("abcdef", "abc"));
    }

    @Test
    void testTwo() {
        assertEquals("12356", LongestCommonSubsequence.lcs("132535365", "123456789"));
    }
}
