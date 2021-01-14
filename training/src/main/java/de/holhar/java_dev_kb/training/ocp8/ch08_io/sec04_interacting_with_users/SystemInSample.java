package de.holhar.java_dev_kb.training.ocp8.ch08_io.sec04_interacting_with_users;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class SystemInSample {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String userInput = reader.readLine();
        println("You entered the following: " + userInput);
    }
}
