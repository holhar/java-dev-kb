package de.holhar.java_dev_kb.katas.codewars.fundamentals;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class StepsInPrimesTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(StepsInPrimesTest.class);

    @Test
    public void test1() {
        LOGGER.debug("Fixed Tests");
        assertEquals("[101, 103]", Arrays.toString(StepsInPrimes.step(2, 100, 110)));
    }

    @Test
    public void test2() {
        LOGGER.debug("Fixed Tests");
        assertEquals("[103, 107]", Arrays.toString(StepsInPrimes.step(4, 100, 110)));
    }

    @Test
    public void test3() {
        LOGGER.debug("Fixed Tests");
        assertEquals("[101, 107]", Arrays.toString(StepsInPrimes.step(6, 100, 110)));
    }

    @Test
    public void test4() {
        LOGGER.debug("Fixed Tests");
        assertEquals("[359, 367]", Arrays.toString(StepsInPrimes.step(8, 300, 400)));
    }

    @Test
    public void test5() {
        LOGGER.debug("Fixed Tests");
        assertEquals("[307, 317]", Arrays.toString(StepsInPrimes.step(10, 300, 400)));
    }
}