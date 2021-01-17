package de.holhar.java_dev_kb.training.oca8.ch01_java_building_blocks;

// SECOND IMPORT DOES NOT COMPILE:
// import java.util.*;
// import java.sql.*; // both packages contain a Date class

// COMPILES (explicit imports take precedence over wildcard imports):

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImportConflicts {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImportConflicts.class);

    public static void main(String[] args) {
        LOGGER.debug("It works!");
    }
}
