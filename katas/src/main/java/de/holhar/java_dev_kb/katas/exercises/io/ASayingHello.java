package de.holhar.java_dev_kb.katas.exercises.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ASayingHello {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ASayingHello.class);

    public static void main(String[] args) throws IOException {
        LOGGER.info("What is your name?");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input = bufferedReader.readLine();
        switch (input) {
            case "Jean":
                LOGGER.info("Salut {}, comment vas-tu?", input);
                break;
            case "Viktor":
                LOGGER.info("Привет {}, как дела?", input);
                break;
            default:
                LOGGER.info("Hello {}, how are you?", input);
        }

    }
}
