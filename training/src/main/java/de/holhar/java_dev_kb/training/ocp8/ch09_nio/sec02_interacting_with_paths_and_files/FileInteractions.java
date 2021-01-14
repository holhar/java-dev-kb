package de.holhar.java_dev_kb.training.ocp8.ch09_nio.sec02_interacting_with_paths_and_files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.getNioHome;
import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class FileInteractions {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileInteractions.class);

    public static void main(String[] args) {
        // Testing a Path with exists()
        Files.exists(Paths.get("/ostrich/feathers.png"));
        Files.exists(Paths.get("src/main/resources/nio-examples"));

        /*
         * Testing uniqueness with isSameFile() - works also for directories
         *
         * - first checks with equals() if two Path references are the same
         * - if this is not the case, tries locate the files in the fileSystem -> throws an exception, if one of the
         *   files does not exist
         */
        try {
            println(Files.isSameFile(
                    Paths.get(getNioHome() + "interacting-with-paths-and-files/food.source"),
                    Paths.get(getNioHome() + "interacting-with-paths-and-files/food.txt")));

            // Not considered as same file, as they're in different locations
            println(Files.isSameFile(
                    Paths.get(getNioHome() + "here/tail.data"),
                    Paths.get(getNioHome() + "there/tail.data")));
        } catch (IOException e) {
            LOGGER.error("Could not check if isSameFile '{}'", e.getMessage());
        }
        println("");

        // Making directories with createDirectory() and createDirectories() -> throws exceptions, if directory
        //  already exists or if the directory could not be created
        try {
            Files.createDirectory(Paths.get(getNioHome() + "a_bla"));
            Files.createDirectories(Paths.get(getNioHome() + "a_blub/bla"));
        } catch (IOException e) {
            LOGGER.error("Could not create directory '{}'", e.getMessage());
        }

        // Duplicating File contents with copy() -> throws an exception, if the file or directory does not exists or
        //  cannot be read; also directory copies are shallow rather than deep!
        try {
            Files.copy(Paths.get(getNioHome() + "there"), Paths.get(getNioHome() + "a_there-copy"));
            Files.copy(Paths.get(getNioHome() + "there/tail.data"), Paths.get(getNioHome() + "a_there-copy/tail.data"));
        } catch (IOException e) {
            LOGGER.error("Could not copy file or directory '{}'", e.getMessage());
        }

        // Copying Files with java.io and NIO.2
        try (InputStream is = new FileInputStream(getNioHome() + "stream-sources/source-data.txt");
             OutputStream os = new FileOutputStream(getNioHome() + "stream-sources/output-data.txt")) {

            // Copy stream data to file
            Files.copy(is, Paths.get(getNioHome() + "stream-destinations/wolf.txt"));

            // Copy file data to stream
            Files.copy(Paths.get(getNioHome() + "stream-destinations/clown.txt"), os);
        } catch (IOException e) {
            LOGGER.error("Could not copy file or directory '{}'", e.getMessage());
        }

        // Changing the File location with move()
        try {
            Files.move(Paths.get(getNioHome() + "here"), Paths.get(getNioHome() + "here-new"));
            Files.move(Paths.get(getNioHome() + "stream-sources/source-data.txt"), Paths.get(getNioHome() + "here-new/source-data.txt"));
        } catch (IOException e) {
            LOGGER.error("Could not move file '{}'", e.getMessage());
        }

        // Removing a File with delete() and deleteIfExists()
        try {
            Files.delete(Paths.get(getNioHome() + "animals/vultures/feathers.txt"));
            Files.deleteIfExists(Paths.get(getNioHome() + "animals/pigeons"));
        } catch (IOException e) {
            LOGGER.error("Could not delete file or directory '{}'", e.getMessage());
        }

        // Reading and Writing File data with newBufferedReader() and newBufferedWriter()
        Path path1 = Paths.get(getNioHome() + "animals/gopher.txt");
        try (BufferedReader reader = Files.newBufferedReader(path1, StandardCharsets.UTF_8)) {
            // Read from the stream
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                println(currentLine);
            }
        } catch (IOException e) {
            LOGGER.error("Could not read file '{}'", e.getMessage());
        }
        println("");

        Path path2 = Paths.get(getNioHome() + "animals/gorillas.txt");
        List<String> data = new ArrayList<>();
        data.add("Gorillas are ground-dwelling, predominantly herbivorous apes that inhabit\n");
        data.add("the forests of central Sub-Saharan Africa. The genus Gorilla is divided \n");
        data.add("into two species: the eastern gorillas and the western gorillas (both \n");
        data.add("critically endangered), and either four or five subspecies. They are the \n");
        data.add("largest living primates. The DNA of gorillas is highly similar to that of \n");
        data.add("humans, from 95 to 99% depending on what is included, and they are the \n");
        data.add("7 next closest living relatives to humans after the chimpanzees and bonobos.");
        // Overwrites file, if it already exists!
        try (BufferedWriter writer = Files.newBufferedWriter(path2, StandardCharsets.UTF_16)) {
            for (String line : data) {
                writer.write(line);
            }
        } catch (IOException e) {
            LOGGER.error("Could not write to file '{}'", e.getMessage());
        }

        // Reading Files with readAllLines()
        Path path3 = Paths.get(getNioHome() + "animals/sharks.txt");
        try {
            final List<String> lines = Files.readAllLines(path3);
            for (String line : lines) {
                println(line);
            }
        } catch (IOException e) {
            LOGGER.error("Could not read file '{}'", e.getMessage());
        }
    }
}
