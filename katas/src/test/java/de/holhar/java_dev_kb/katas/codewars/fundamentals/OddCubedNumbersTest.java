package de.holhar.java_dev_kb.katas.codewars.fundamentals;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OddCubedNumbersTest {

    @Test
    public void cubeOdd() throws Exception {
        int[] given = new int[]{1, 2, 3};
        assertEquals(28, OddCubedNumbers.cubeOdd(given));
    }

}