package de.holhar.java_dev_kb.training.oca8.ch02_operators_and_statements.twist_in_tale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogicalOperators {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogicalOperators.class);

    public static void main(String[] args) {
        int a = 10, b = 20, c = 40;

        LOGGER.debug("{}", a++ > 10 || ++b < 30); // true: both execute
        LOGGER.debug("{}", a > 90 && ++b < 30); // false: only first executes
        LOGGER.debug("{}", !(c > 20) && a == 10); // false: only first executes
        LOGGER.debug("{}", a >= 99 || a <= 33 && b == 10); // false: all execute
        LOGGER.debug("{}", a >= 99 && a <= 33 || b == 10); // false: first and last execute
    }
}
