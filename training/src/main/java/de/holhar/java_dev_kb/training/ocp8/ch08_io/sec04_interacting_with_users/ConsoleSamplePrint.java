package de.holhar.java_dev_kb.training.ocp8.ch08_io.sec04_interacting_with_users;

import java.io.Console;
import java.io.IOException;

public class ConsoleSamplePrint {

    public static void main(String[] args) throws NumberFormatException, IOException {
        Console console = System.console();
        if (console == null) {
            throw new RuntimeException("Console not available");
        } else {
            console.writer().println("Welcome to Our Zoo!");
            console.format("Our zoo has 291 animals and employs 25 people.");
            console.writer().println();
            console.printf("The zoo spans 128.91 acres.");
        }
    }
}
