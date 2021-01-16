package de.holhar.java_dev_kb.training.cleancode.successiveRefinement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CleanCodeApp {

    private static final Logger LOGGER = LoggerFactory.getLogger(CleanCodeApp.class);

    public static void main(String[] args) {
        try {
            Args arg = new Args("l,p#,d*", args);
            boolean logging = arg.getBoolean('l');
            int port = arg.getInt('p');
            String directory = arg.getString('d');
            executeApplication(logging, port, directory);
        } catch (ArgsException e) {
            LOGGER.debug("Argument error: {}\n", e.errorMessage());
        }
    }

    private static void executeApplication(boolean logging, int port, String directory) {
        LOGGER.debug("Executing Application with the following configuration:");
        LOGGER.debug("- logging: {}", logging);
        LOGGER.debug("- port: {}", port);
        LOGGER.debug("- directory: {}", directory);
    }
}