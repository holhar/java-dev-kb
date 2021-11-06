package de.holhar.java_dev_kb.katas.codewars.algorithms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidBracesTest {

    private final ValidBraces checker = new ValidBraces();

    @Test
    void testValid() {
        assertTrue(checker.isValid("()"));
    }

    @Test
    void testValid2() {
        assertTrue(checker.isValid("({})[({})]"));
    }

    @Test
    void testInvalid() {
        assertFalse(checker.isValid("[(])"));
    }

    @Test
    void testInvalid2() {
        assertFalse(checker.isValid("())({}}{()][]["));
    }

    @Test
    void testInvalid3() {
        assertFalse(checker.isValid("(((({{"));
    }
}
