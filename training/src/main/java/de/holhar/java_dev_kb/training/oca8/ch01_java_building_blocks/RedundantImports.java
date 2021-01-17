package de.holhar.java_dev_kb.training.oca8.ch01_java_building_blocks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
// import separately
// import java.nio.Files;
// import java.nio.Paths;

// NO GOOD:
// import java.nio.*; // a wildcard only matches class names, not "file.*Files"
// import java.nio.*.*; // you can only have one wildcard and it must be at the end
// import java.nio.files.Paths.*; // you cannot import methods, only class names

class ImportExample {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImportExample.class);

    public static void main(String[] args) {
        Random r = new Random();
        LOGGER.debug("{}", r.nextInt(10));
    }

    public void read(Files files) {
        Paths.get("name");
    }
}
