package de.holhar.java_dev_kb.training.oca8.ch01_java_building_blocks;

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
    public static void main(String[] args) {
        Random r = new Random();
        System.out.println(r.nextInt(10));
    }

    public void read(Files files) {
        Paths.get("name");
    }
}
