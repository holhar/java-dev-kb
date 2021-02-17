package de.holhar.java_dev_kb.katas.exercises.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class CPrintingQuotes {

    private static final Logger LOGGER = LoggerFactory.getLogger(CPrintingQuotes.class);

    private static final Map<String, String> quotes = new HashMap<>();

    static {
        quotes.put("Peter Venkman", "Mass hysteria, human sacrifice, cats and dogs living together!");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        LOGGER.info("What is the quote?");
        String quote = reader.readLine();
        LOGGER.info("Who said it?");
        quotes.put(reader.readLine(), quote);

        quotes.entrySet().forEach(entry -> LOGGER.info("{} says, \"{}\"", entry.getKey(), entry.getValue()));

    }
}
