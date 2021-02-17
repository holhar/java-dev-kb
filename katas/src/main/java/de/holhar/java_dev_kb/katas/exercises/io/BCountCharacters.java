package de.holhar.java_dev_kb.katas.exercises.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BCountCharacters {

    private static final Logger LOGGER = LoggerFactory.getLogger(BCountCharacters.class);

    public static void main(String[] args) throws IOException {
        LOGGER.info("What is the input String?");
        String input = getString();
        while (input.isBlank()) {
            LOGGER.info("Please enter a non-blank String");
            input = getString();
        }
        LOGGER.info("'{}' has {} characters", input, input.length());
    }

    private static String getString() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        return bufferedReader.readLine();
    }
}
