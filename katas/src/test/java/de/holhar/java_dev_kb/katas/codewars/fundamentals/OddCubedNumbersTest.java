package de.holhar.java_dev_kb.katas.codewars.fundamentals;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OddCubedNumbersTest {

    @Test
    void cubeOdd() throws Exception {
        int[] given = new int[]{1, 2, 3};
        assertEquals(28, OddCubedNumbers.cubeOdd(given));
    }

}