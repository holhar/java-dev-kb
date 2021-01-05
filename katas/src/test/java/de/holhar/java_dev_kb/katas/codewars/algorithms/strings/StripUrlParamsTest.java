package de.holhar.java_dev_kb.katas.codewars.algorithms.strings;

import org.junit.Test;

import static de.holhar.java_dev_kb.katas.codewars.algorithms.strings.StripUrlParams.stripUrlParams;
import static junit.framework.TestCase.assertEquals;

public class StripUrlParamsTest {

    @Test
    public void testStripUrlParams1() {
        assertEquals("www.codewars.com?a=1&b=2", stripUrlParams("www.codewars.com?a=1&b=2&a=2"));
    }

    @Test
    public void testStripUrlParams2() {
        assertEquals("www.codewars.com?a=1", stripUrlParams("www.codewars.com?a=1&b=2&a=2", "b"));
    }

    @Test
    public void testStripUrlParams3() {
        assertEquals("www.codewars.com", stripUrlParams("www.codewars.com", "b"));
    }
}