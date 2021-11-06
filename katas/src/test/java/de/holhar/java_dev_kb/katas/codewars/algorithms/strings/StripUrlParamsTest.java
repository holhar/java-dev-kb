package de.holhar.java_dev_kb.katas.codewars.algorithms.strings;

import org.junit.jupiter.api.Test;

import static de.holhar.java_dev_kb.katas.codewars.algorithms.strings.StripUrlParams.stripUrlParams;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StripUrlParamsTest {

    @Test
    void testStripUrlParams1() {
        assertEquals("www.codewars.com?a=1&b=2", stripUrlParams("www.codewars.com?a=1&b=2&a=2"));
    }

    @Test
    void testStripUrlParams2() {
        assertEquals("www.codewars.com?a=1", stripUrlParams("www.codewars.com?a=1&b=2&a=2", "b"));
    }

    @Test
    void testStripUrlParams3() {
        assertEquals("www.codewars.com", stripUrlParams("www.codewars.com", "b"));
    }
}