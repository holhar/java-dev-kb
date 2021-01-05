package de.holhar.java_dev_kb.katas.codewars.regex;

import org.junit.Ignore;
import org.junit.Test;

import java.util.regex.Matcher;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Ignore
public class SplittingPhoneNumbersTest {

    public static void assertNumber(String number, String g1, String g2, String g3) {
        Matcher m = SplittingPhoneNumbers.createPattern().matcher(number);
        assertTrue("couldn't match with: " + number, m.matches());
        assertEquals("couldn't match first group", g1, m.group(1));
        assertEquals("couldn't match second group", g2, m.group(2));
        assertEquals("couldn't match third group", g3, m.group(3));
    }

    @Test
    public void fullNumberTest() {
        assertNumber("+12 34 567890", "12", "34", "567890");
    }

    @Test
    public void fullNumberTest1() {
        assertNumber("0001 34 567890", "12", "34", "567890");
    }

    @Test
    public void fullNumberTest2() {
        assertNumber("+01 34 567890", "12", "34", "567890");
    }

    @Test
    public void fullNumberTest3() {
        assertNumber("+12 04 567890", "12", "34", "567890");
    }

    @Test
    public void fullNumberTest4() {
        assertNumber("+12 034 567890", "12", "34", "567890");
    }

    @Test
    public void fullNumberTest5() {
        assertNumber("+12 34 067895", "12", "34", "567890");
    }

    @Test
    public void fullNumberTest6() {
        assertNumber("034 067895", "12", "34", "567890");
    }

    @Test
    public void fullNumberTest7() {
        assertNumber("001 567890", "12", "34", "567890");
    }

    @Test
    public void fullNumberTest8() {
        assertNumber("098765", "12", "34", "567890");
    }
}