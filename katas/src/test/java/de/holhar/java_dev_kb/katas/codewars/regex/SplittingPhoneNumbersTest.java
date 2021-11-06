package de.holhar.java_dev_kb.katas.codewars.regex;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
class SplittingPhoneNumbersTest {

    public static void assertNumber(String number, String g1, String g2, String g3) {
        Matcher m = SplittingPhoneNumbers.createPattern().matcher(number);
        assertTrue(m.matches(), "couldn't match with: " + number);
        assertEquals("couldn't match first group", g1, m.group(1));
        assertEquals("couldn't match second group", g2, m.group(2));
        assertEquals("couldn't match third group", g3, m.group(3));
    }

    @Test
    void fullNumberTest00() {
        assertNumber("+12 34 567890", "12", "34", "567890");
    }

    @Test
    void fullNumberTest01() {
        assertNumber("0012 34 567890", "12", "34", "567890");
    }

    @Test
    void fullNumberTest02() {
        assertNumber("034 567890", null, "34", "567890");
    }

    @Test
    void fullNumberTest03() {
        assertNumber("567890", null, null, "567890");
    }

    @Test
    void fullNumberTest04() {
        var number = " 567890";
        Matcher m = SplittingPhoneNumbers.createPattern().matcher(number);
        assertFalse(m.matches());
    }

    /*
    @Test
    void fullNumberTest1() {
        assertNumber("0001 34 567890", "12", "34", "567890");
    }

    @Test
    void fullNumberTest2() {
        assertNumber("+01 34 567890", "12", "34", "567890");
    }

    @Test
    void fullNumberTest3() {
        assertNumber("+12 04 567890", "12", "34", "567890");
    }

    @Test
    void fullNumberTest4() {
        assertNumber("+12 034 567890", "12", "34", "567890");
    }

    @Test
    void fullNumberTest5() {
        assertNumber("+12 34 067895", "12", "34", "567890");
    }

    @Test
    void fullNumberTest6() {
        assertNumber("034 067895", "12", "34", "567890");
    }

    @Test
    void fullNumberTest7() {
        assertNumber("001 567890", "12", "34", "567890");
    }

    @Test
    void fullNumberTest8() {
        assertNumber("098765", "12", "34", "567890");
    }
    */
}