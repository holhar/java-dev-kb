package de.holhar.java_dev_kb.algorithms.sorting;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class QuickSortTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuickSortTest.class);

    private final static int SIZE = 7;
    private final static int MAX = 20;
    private int[] numbers;

    @Before
    public void setUp() throws Exception {
        numbers = new int[SIZE];
        Random generator = new Random();
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = generator.nextInt(MAX);
        }
    }

    @Test
    public void testNull() {
        QuickSort sorter = new QuickSort();
        sorter.sort(null);
    }

    @Test
    public void testEmpty() {
        QuickSort sorter = new QuickSort();
        sorter.sort(new int[0]);
    }

    @Test
    public void testSimpleElement() {
        QuickSort sorter = new QuickSort();
        int[] test = new int[1];
        test[0] = 5;
        sorter.sort(test);
    }

    @Test
    public void testSpecial() {
        QuickSort sorter = new QuickSort();
        int[] test = {5, 5, 6, 6, 4, 4, 5, 5, 4, 4, 6, 6, 5, 5};
        sorter.sort(test);
        if (!validate(test)) {
            fail("Should not happen");
        }
        printResult(test);
    }

    @Test
    public void testQuickSort() {
        for (Integer i : numbers) {
            LOGGER.debug(i + " ");
        }
        long startTime = System.currentTimeMillis();

        QuickSort sorter = new QuickSort();
        sorter.sort(numbers);

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        LOGGER.debug("QuickSort " + elapsedTime);

        if (!validate(numbers)) {
            fail("Should not happen");
        }
        assertTrue(true);
    }

    @Test
    public void testStandardSort() {
        long startTime = System.currentTimeMillis();
        Arrays.sort(numbers);
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        LOGGER.debug("Standard Java sort " + elapsedTime);
        if (!validate(numbers)) {
            fail("Should not happen");
        }
        assertTrue(true);
    }

    private boolean validate(int[] numbers) {
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] > numbers[i + 1]) {
                return false;
            }
        }
        return true;
    }

    private void printResult(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            LOGGER.debug("{}", numbers[i]);
        }
    }
}
