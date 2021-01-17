package de.holhar.java_dev_kb.training.oca8.ch01_java_building_blocks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Chick {

    private static final Logger LOGGER = LoggerFactory.getLogger(Chick.class);

    private String name = "Fluffy";

    // Order matters for the fields and blocks of code => DOES NOT COMPILE:
    {
        // LOGGER.debug(name);
    }

    // An instance initializer block
    // Fields and instance initializer blocks are run in the order in which they appear in the file.
    {
        LOGGER.debug("setting field");
    }

    {
        LOGGER.debug("Another instance initializer");
    }

    // The constructor runs after all fields and instance initializer blocks have run.
    public Chick() {
        name = "Tiny";
        LOGGER.debug("setting constructor");
    }

    public static void main(String[] args) {
        Chick chick = new Chick();
        LOGGER.debug(chick.name);
    }
}
