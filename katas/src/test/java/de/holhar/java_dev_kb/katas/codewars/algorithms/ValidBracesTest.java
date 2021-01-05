package de.holhar.java_dev_kb.katas.codewars.algorithms;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidBracesTest {

    private final ValidBraces checker = new ValidBraces();

    @Test
    public void testValid() {
        assertTrue(checker.isValid("()"));
    }

    @Test
    public void testValid2() {
        assertTrue(checker.isValid("({})[({})]"));
    }

    @Test
    public void testInvalid() {
        assertFalse(checker.isValid("[(])"));
    }

    @Test
    public void testInvalid2() {
        assertFalse(checker.isValid("())({}}{()][]["));
    }

    @Test
    public void testInvalid3() {
        assertFalse(checker.isValid("(((({{"));
    }
}
