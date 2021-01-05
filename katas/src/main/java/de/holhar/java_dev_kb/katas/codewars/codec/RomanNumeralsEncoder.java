package de.holhar.java_dev_kb.katas.codewars.codec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Roman Numerals Encoder (https://www.codewars.com/kata/51b62bf6a9c58071c600001b)
 * <p>
 * Create a function taking a positive integer as its parameter and returning a string containing the Roman Numeral
 * representation of that integer.
 * Modern Roman numerals are written by expressing each digit separately starting with the left most digit and
 * skipping any digit with a value of zero. In Roman numerals 1990 is rendered: 1000=M, 900=CM, 90=XC; resulting in
 * MCMXC. 2008 is written as 2000=MM, 8=VIII; or MMVIII. 1666 uses each Roman symbol in descending order: MDCLXVI.
 * <p>
 * Example:
 * <p>
 * conversion.solution(1000); //should return "M"
 * <p>
 * Help:
 * <p>
 * Symbol    Value
 * I          1
 * V          5
 * X          10
 * L          50
 * C          100
 * D          500
 * M          1,000
 * <p>
 * Remember that there can't be more than 3 identical symbols in a row.
 * <p>
 * More about roman numerals - http://en.wikipedia.org/wiki/Roman_numerals
 * <p>
 * TODO: Finish me!
 */
public class RomanNumeralsEncoder {

    private static Logger LOGGER = LoggerFactory.getLogger(RomanNumeralsEncoder.class);

    public String solution(int n) {
        LOGGER.debug("{}", 900 % 1000);
        return "";
    }
}
