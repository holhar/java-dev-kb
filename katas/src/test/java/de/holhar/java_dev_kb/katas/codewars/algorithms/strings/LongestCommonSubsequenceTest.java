package de.holhar.java_dev_kb.katas.codewars.algorithms.strings;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LongestCommonSubsequenceTest {

    @Test
    public void exampleTests() {
        assertEquals("", LongestCommonSubsequence.lcs("a", "b"));
        assertEquals("abc", LongestCommonSubsequence.lcs("abcdef", "abc"));
    }

    @Test
    public void testTwo() {
        assertEquals("12356", LongestCommonSubsequence.lcs("132535365", "123456789"));
    }
}
