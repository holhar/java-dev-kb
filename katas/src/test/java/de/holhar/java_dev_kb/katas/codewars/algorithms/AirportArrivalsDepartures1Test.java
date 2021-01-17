package de.holhar.java_dev_kb.katas.codewars.algorithms;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertArrayEquals;

public class AirportArrivalsDepartures1Test {

    private static final Logger LOGGER = LoggerFactory.getLogger(AirportArrivalsDepartures1Test.class);

    private String[] BEFORE(String[] a) {
        LOGGER.debug("Before:");
        return a;
    }

    private String[] AFTER(String[] a) {
        LOGGER.debug("After:");
        return a;
    }

    @Test
    public void example() {
        // CAT => DOG
        String[] before = BEFORE(new String[]{"CAT"});
        int[][] rotors = new int[][]{{1, 13, 27}};
        String[] after = AFTER(AirportArrivalsDepartures1.flapDisplay(before, rotors));
        String[] expected = new String[]{"DOG"};
        assertArrayEquals(expected, after);
    }

    @Test
    public void basic() {
        // HELLO => WORLD!
        String[] before = BEFORE(new String[]{"HELLO "});
        int[][] rotors = new int[][]{{15, 49, 50, 48, 43, 13}};
        String[] after = AFTER(AirportArrivalsDepartures1.flapDisplay(before, rotors));
        String[] expected = new String[]{"WORLD!"};
        assertArrayEquals(expected, after);
    }

    @Test
    public void basic2() {
        // CODE => WARS
        String[] before = BEFORE(new String[]{"CODE"});
        int[][] rotors = new int[][]{{20, 20, 28, 0}};
        String[] after = AFTER(AirportArrivalsDepartures1.flapDisplay(before, rotors));
        String[] expected = new String[]{"WARS"};
        assertArrayEquals(expected, after);
    }

    @Test
    public void arrayInput() {
        String[] before = BEFORE(new String[]{"CAT", "HELLO ", "CODE"});
        int[][] rotors = new int[][]{{1, 13, 27}, {15, 49, 50, 48, 43, 13}, {20, 20, 28, 0}};
        String[] after = AFTER(AirportArrivalsDepartures1.flapDisplay(before, rotors));
        String[] expected = new String[]{"DOG", "WORLD!", "WARS"};
        assertArrayEquals(expected, after);
    }
}