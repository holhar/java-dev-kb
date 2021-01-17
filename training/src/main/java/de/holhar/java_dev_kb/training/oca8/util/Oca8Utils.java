package de.holhar.java_dev_kb.training.oca8.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Oca8Utils {

    private static final Logger LOGGER = LoggerFactory.getLogger(Oca8Utils.class);

    public static void print(Object value) {
        LOGGER.debug("{}", value);
    }
}
