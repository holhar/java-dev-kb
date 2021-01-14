package de.holhar.java_dev_kb.training.ocp8.ch08_io.sec02_streams_basics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.*;

public class StreamBasics {

    private static final Logger LOGGER = LoggerFactory.getLogger(StreamBasics.class);

    public static void main(String[] args) {

        /*
         * Low-Level vs. High-Level Streams
         */
        // Wrapping a low-level stream in high-level stream for performance reasons
        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader(getIoHome() + "buecher-liste.txt"))) {
            println(bufferedReader.readLine());
        } catch (IOException e) {
            LOGGER.error("Could not read file under: " + getIoHome(), e);
        }

        // High-level streams can take other high-level streams as input to further abstract away functionality
        try (ObjectInputStream objectStream = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(getIoHome() + "buecher-liste.txt")))) {
            println(objectStream.readObject());
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.error("Could not find or deserialize data under: " + getIoHome(), e);
        }

        // NOT COMPILING stream examples
        // new BufferedInputStream(new FileReader("zoo-data.txt")); // Mixing Stream and Reader class
        // new BufferedWriter(new FileOutputStream("zoo-data.txt")); // Mixing Stream and Writer class
        // new ObjectInputStream(new FileOutputStream("zoo-data.txt")); // Mising Input and Output stream classes
        // new BufferedInputStream(new InputStream()); // Trying to instantiate abstract parent class InputStream

        /*
         * Common Stream Operations
         */
        BufferedInputStream is = null;
        try {
            is = new BufferedInputStream(new FileInputStream(getIoHome() + "buecher-liste.txt"));
            println("Is mark supported?: " + is.markSupported());
            // Marking the stream
            print((char) is.read());
            if (is.markSupported()) {
                is.mark(100);
                print((char) is.read());
                print((char) is.read());
                is.reset();
            }
            print((char) is.read());
            print((char) is.read());
            print((char) is.read());

            // Skipping over data
            is.skip(3);
            is.read();
            print((char) is.read());
            print((char) is.read());
        } catch (IOException e) {
            LOGGER.error("Could not read file", e);
        } finally {
            if (is != null) {
                try {
                    // Closing the stream (old-fashioned way)
                    is.close();
                } catch (IOException e) {
                    // ignore...
                }
            }
        }
    }
}
