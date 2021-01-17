package de.holhar.java_dev_kb.training.oca8.ch01_java_building_blocks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrimitivePlayground {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrimitivePlayground.class);

    public static void main(String[] args) {
        // long max = 3123456789; => too large for an integer - DOES NOT COMPILE
        long max = 3123456789L;

        // Valid usages of number systems
        LOGGER.debug("{}", 56);      // decimal
        LOGGER.debug("{}", 0b11);    // binary
        LOGGER.debug("{}", 017);     // octal
        LOGGER.debug("{}", 0x1F);    // hexadecimal

        // Invalid usages of underscores in numbers
        //double notAtStart = _1000:00;
        //double notAtEnd = 1000.00_;
        //double notByDecimal = 1000_.00;

        // This one compiles:
        double annoyingButLegal = 1_00_0.0_0;
    }
}
