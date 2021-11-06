package de.holhar.java_dev_kb.katas.codewars.codec;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled
class RomanNumeralsEncoderTest {

    private RomanNumeralsEncoder conversion = new RomanNumeralsEncoder();

    @Test
    void shouldCovertToRoman_simpleTest1() {
        assertEquals("solution(1) should equal to I", "I", conversion.solution(1));
    }

    @Test
    void shouldCovertToRoman_simpleTest2() {
        assertEquals("solution(4) should equal to IV", "IV", conversion.solution(4));
    }

    @Test
    void shouldCovertToRoman_simpleTest3() {
        assertEquals("solution(6) should equal to VI", "VI", conversion.solution(6));
    }

    @Test
    void shouldCovertToRoman_hardTest1() {
        assertEquals("solution(1990) should equal to MCMXC", "MCMXC", conversion.solution(1990));
    }

    @Test
    void shouldCovertToRoman_hardTest2() {
        assertEquals("solution(2008) should equal to MMVIII", " MMVIII", conversion.solution(2008));
    }

    @Test
    void shouldCovertToRoman_hardTest3() {
        assertEquals("solution(1666) should equal to MDCLXVI", "MDCLXVI", conversion.solution(1666));
    }
}