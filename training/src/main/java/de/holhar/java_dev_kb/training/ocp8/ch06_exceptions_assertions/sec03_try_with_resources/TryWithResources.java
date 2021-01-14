package de.holhar.java_dev_kb.training.ocp8.ch06_exceptions_assertions.sec03_try_with_resources;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * @author hhs@dasburo.com
 */
public class TryWithResources {

    public static void main(String[] args) throws IOException {
        Path p1 = Paths.get("file1.txt");
        Path p2 = Paths.get("file2.txt");

        oldApproach(p1, p2);

        newApproach(p1, p2);

        resourceScopes();
    }

    private static void oldApproach(Path p1, Path p2) throws IOException {
        BufferedReader in = null;
        BufferedWriter out = null;
        try {
            in = Files.newBufferedReader(p1);
            out = Files.newBufferedWriter(p2);
            out.write(in.readLine());
        } finally {
            if (in != null) in.close();
            if (out != null) out.close();
        }
    }

    private static void newApproach(Path p1, Path p2) throws IOException {
        try (BufferedReader in = Files.newBufferedReader(p1);
             BufferedWriter out = Files.newBufferedWriter(p2)) {
            out.write(in.readLine());
        }
        // NO ADDITIONAL STUFF NEEDED - there's an implicit finally clause, that closes the resources
        // OPTIONAL - runs after implicit finally clause:
        catch (IllegalArgumentException e) {
            // do stuff
        } finally {
            // do stuff
        }
    }

    private static void resourceScopes() {
        try (Scanner scanner = new Scanner(System.in)) {
            // Scanner is a local variable, only in scope within the try-clause
            scanner.nextLine();
        } catch (Exception e) {
            // scanner.nextInt(); // DOES NOT COMPILE
        } finally {
            // scanner.nextInt(); // DOES NOT COMPILE
        }
    }
}
