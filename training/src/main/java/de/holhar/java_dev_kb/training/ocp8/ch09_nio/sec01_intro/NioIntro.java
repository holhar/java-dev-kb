package de.holhar.java_dev_kb.training.ocp8.ch09_nio.sec01_intro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NioIntro {

    private static final Logger LOGGER = LoggerFactory.getLogger(NioIntro.class);

    public static void main(String[] args) throws URISyntaxException {
        /*
         * Creating Paths
         */
        // Creating Paths with static methods of utility class 'Paths'
        Path path1 = Paths.get("pandas/cuddly.png");

        // Creating Paths with a list of String values
        Path path2 = Paths.get("pandas", "cuddly.png");

        // Creating Paths with an URI value
        try {
            // Throws Exception at runtime - must be an absolute path
            Path path3 = Paths.get(new URI("file://pandas/cuddly.png"));
        } catch (Exception e) {
            LOGGER.error("Could not create path from URI: '{}'", e.getMessage());
        }
        Path path4 = Paths.get(new URI("file:///pandas/cuddly.png"));

        // Paths with other types of non-local file system schemas
        try {
            Path path5 = Paths.get(new URI("http://www.wiley.com"));
        } catch (Exception e) {
            LOGGER.error("Could not create path from URI: '{}'", e.getMessage());
        }

        try {
            Path path6 = Paths.get(new URI("ftp://user:password@ftp.the-ftp-server.com"));
        } catch (Exception e) {
            LOGGER.error("Could not create path from URI: '{}'", e.getMessage());
        }

        // The Path interface contains a reciprocal method toUri()
        URI uri5 = path4.toUri();

        /*
         * Accessing the underlying FileSystem object
         *
         * The Paths.get() method used throughout the previous examples is actually shorthand for the class
         * java.nio.file.FileSystem method getPath()
         */
        Path path7 = FileSystems.getDefault().getPath("pandas/cuddly.png");

        // The FileSystems factory class does give us the ability to connect to a remote file system
        try {
            FileSystem fileSystem = FileSystems.getFileSystem(new URI("http://www.selikoff.net"));
            Path path8 = fileSystem.getPath("duck.txt");
        } catch (Exception e) {
            LOGGER.error("Could not create path from URI: '{}'", e.getMessage());
        }

        /*
         * Working with legacy File instances
         */
        File file1 = new File("pandas/cuddly.png");
        Path path9 = file1.toPath();

        File file2 = path9.toFile();
    }
}
