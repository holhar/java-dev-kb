package de.holhar.java_dev_kb.training.ocp8.ch09_nio.sec04_new_stream_methods;

import de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.getNioHome;
import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class NewStreamMethods {

    private static final Logger LOGGER = LoggerFactory.getLogger(NewStreamMethods.class);

    public static void main(String[] args) {
        // Walking a directory
        Path path1 = Paths.get(getNioHome() + "animals");
        try {
            Files.walk(path1)
                    .filter(p -> p.toString().endsWith(".txt"))
                    .forEach(OcpPrepUtils::println);
        } catch (IOException e) {
            LOGGER.error("Directory walk failed '{}'", e.getMessage());
        }
        println("");

        // Searching a directory
        long dateFilter = 1570550566000L;
        try {
            Stream<Path> stream = Files.find(path1, 10,
                    (p, a) -> p.toString().endsWith(".txt")
                            && a.lastModifiedTime().toMillis() > dateFilter);
            stream.forEach(OcpPrepUtils::println);
        } catch (IOException e) {
            LOGGER.error("Directory search failed '{}'", e.getMessage());
        }
        println("");

        // Listing directory contents
        try {
            Files.list(path1)
                    .filter(p -> !Files.isDirectory(p))
                    .map(Path::toAbsolutePath)
                    .forEach(OcpPrepUtils::println);
        } catch (IOException e) {
            LOGGER.error("Directory search failed '{}'", e.getMessage());
        }
        println("");

        // Printing file contents
        Path path2 = Paths.get(getNioHome() + "animals/some.log");
        try {
            // Print whole content
            Files.lines(path2).forEach(OcpPrepUtils::println);
            println("");

            // Filter content
            List<String> result = Files.lines(path2)
                    .filter(line -> line.startsWith("WARN"))
                    .map(line -> line.substring(5))
                    .collect(Collectors.toList());

            println(result);
        } catch (IOException e) {
            LOGGER.error("Could not read file content '{}'", e.getMessage());
        }
    }
}
