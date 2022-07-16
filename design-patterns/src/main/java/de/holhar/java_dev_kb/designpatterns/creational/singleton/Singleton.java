package de.holhar.java_dev_kb.designpatterns.creational.singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Singleton {

    private static final Logger logger = LoggerFactory.getLogger(Singleton.class);

    private static Singleton instance;

    // Private constructor to prevent the use of "new"
    private Singleton() {}

    public static synchronized Singleton getInstance() {
        // Lazy initialization
        if (instance == null) {
            logger.info("Create new Singleton instance");
            instance = new Singleton();
        }
        return instance;
    }
}
