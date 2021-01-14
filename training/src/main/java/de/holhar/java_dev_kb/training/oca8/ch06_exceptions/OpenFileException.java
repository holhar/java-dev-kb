package de.holhar.java_dev_kb.training.oca8.ch06_exceptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class OpenFileException {
    public static void main(String[] args) throws FileNotFoundException {
        // Throws checked Exception FileNotFoundException
        FileInputStream fis = new FileInputStream("file.txt");
    }
}
