package de.holhar.java_dev_kb.katas.codewars.algorithms.strings;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConvertToCamelCaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConvertToCamelCaseTest.class);

    @Test
    void testSomeUnderscoreLowerStart() {
        String input = "the_Stealth_Warrior";
        LOGGER.debug("input: {}", input);
        assertEquals("theStealthWarrior", ConvertToCamelCase.toCamelCase(input));
    }

    @Test
    void testSomeDashLowerStart() {
        String input = "the-Stealth-Warrior";
        LOGGER.debug("input: {}", input);
        assertEquals("theStealthWarrior", ConvertToCamelCase.toCamelCase(input));
    }

    @Test
    void testDashLowerWordsOnly() {
        String input = "the-stealth-warrior";
        LOGGER.debug("input: {}", input);
        assertEquals("theStealthWarrior", ConvertToCamelCase.toCamelCase(input));
    }
}