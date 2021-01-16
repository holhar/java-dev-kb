package de.holhar.java_dev_kb.training.effectivejava.item48;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class NaiveMonetaryCalculation {

    private static final Logger LOGGER = LoggerFactory.getLogger(NaiveMonetaryCalculation.class);

    public static void main(String[] args) {
        LOGGER.debug("{}", 1.03 - .42);
        LOGGER.debug("{}", 1.00 - 9 * .10);
    }
}
