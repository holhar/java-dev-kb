package de.holhar.java_dev_kb.katas.codewars.algorithms.numbers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Next bigger number with the same digits (https://www.codewars.com/kata/55983863da40caa2c900004e)
 * <p>
 * Create a function that takes a positive integer and returns the next bigger number that can be formed by
 * rearranging its digits. For example:
 * <p>
 * 12 ==> 21
 * 513 ==> 531
 * 2017 ==> 2071
 * <p>
 * nextBigger(num: 12)   // returns 21
 * nextBigger(num: 513)  // returns 531
 * nextBigger(num: 2017) // returns 2071
 * <p>
 * If the digits can't be rearranged to form a bigger number, return -1 (or nil in Swift):
 * <p>
 * 9 ==> -1
 * 111 ==> -1
 * 531 ==> -1
 * <p>
 * nextBigger(num: 9)   // returns nil
 * nextBigger(num: 111) // returns nil
 * nextBigger(num: 531) // returns nil
 */
public class NextBiggerNumber {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(NextBiggerNumber.class);
    
    private NextBiggerNumber() {}

    public static long nextBiggerNumber(long n) {
        LOGGER.debug("get next bigger long for: {}", n);
        char[] digits = String.valueOf(n).toCharArray();
        for (int i = digits.length - 1; i > 0; i--) {
            if (digits[i] > digits[i - 1]) {
                LOGGER.debug("{} at index {} is bigger than {} at index {}", digits[i], i, digits[i - 1], i - 1);
                int nBiggerIndex = i;
                for (int j = digits.length - 1; j > i; j--) {
                    if (digits[j] > digits[i - 1]) {
                        LOGGER.debug("{} at index {} is bigger than {} at index {}", digits[j], j, digits[i - 1], i - 1);
                        nBiggerIndex = j;
                        break;
                    }
                }
                LOGGER.debug("nBiggerIndex: {}", nBiggerIndex);
                LOGGER.debug("nSmallerIndex: {}", (i - 1));
                LOGGER.debug("put {} at index {} and put {} at index {}", digits[(i - 1)], nBiggerIndex, digits[nBiggerIndex], i - 1);
                LOGGER.debug("also sorted the rest of the array after index {}", i - 1);
                char temp = digits[nBiggerIndex];
                digits[nBiggerIndex] = digits[i - 1];
                digits[i - 1] = temp;
                Arrays.sort(digits, i, digits.length);
                LOGGER.debug("result: {}", digits);
                LOGGER.debug("=====================");
                return Long.parseLong(new String(digits));
            }
        }
        return -1;
    }
}
