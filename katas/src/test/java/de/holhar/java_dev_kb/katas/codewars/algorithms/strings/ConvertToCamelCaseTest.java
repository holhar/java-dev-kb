package de.holhar.java_dev_kb.katas.codewars.algorithms.strings;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConvertToCamelCaseTest {

    @Test
    public void testSomeUnderscoreLowerStart() {
        String input = "the_Stealth_Warrior";
        System.out.println("input: " + input);
        assertEquals("theStealthWarrior", ConvertToCamelCase.toCamelCase(input));
    }

    @Test
    public void testSomeDashLowerStart() {
        String input = "the-Stealth-Warrior";
        System.out.println("input: " + input);
        assertEquals("theStealthWarrior", ConvertToCamelCase.toCamelCase(input));
    }

    @Test
    public void testDashLowerWordsOnly() {
        String input = "the-stealth-warrior";
        System.out.println("input: " + input);
        assertEquals("theStealthWarrior", ConvertToCamelCase.toCamelCase(input));
    }
}