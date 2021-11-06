package de.holhar.java_dev_kb.katas.codewars.algorithms.numbers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NextBiggerNumberTest {

    @Test
    void basicTests() {
        assertEquals(21, NextBiggerNumber.nextBiggerNumber(12));
        assertEquals(531, NextBiggerNumber.nextBiggerNumber(513));
        assertEquals(2071, NextBiggerNumber.nextBiggerNumber(2017));
        assertEquals(441, NextBiggerNumber.nextBiggerNumber(414));
        assertEquals(414, NextBiggerNumber.nextBiggerNumber(144));
        assertEquals(1074236, NextBiggerNumber.nextBiggerNumber(1073642));
        assertEquals(1372046, NextBiggerNumber.nextBiggerNumber(1370642));
    }
}
