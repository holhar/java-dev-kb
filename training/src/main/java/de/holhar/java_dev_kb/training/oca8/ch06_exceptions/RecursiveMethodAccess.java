package de.holhar.java_dev_kb.training.oca8.ch06_exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RecursiveMethodAccess {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecursiveMethodAccess.class);

    public static void main(String[] args) {
        // Throws unchecked error StackOverflowError
        myMethod();
    }

    private static void myMethod() {
        LOGGER.debug("myMethod");
        myMethod();
    }
}
