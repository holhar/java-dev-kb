package de.holhar.java_dev_kb.training.ocp8.ch09_nio.sec03_file_attributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.getNioHome;
import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class BasicFileAttributesSample {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get(getNioHome() + "stream-sources/source-data.txt");
        BasicFileAttributes data = Files.readAttributes(path, BasicFileAttributes.class);

        println("Is path a directory?: " + data.isDirectory());
        println("Is path a regular file?: " + data.isRegularFile());
        println("Is path a symbolic link?: " + data.isSymbolicLink());
        println("Path is not a file, directory, nor symbolic link?: " + data.isOther());

        println("Size (in bytes): " + data.size());

        println("Creation date/time: " + data.creationTime());
        println("Last modified date/time: " + data.lastModifiedTime());
        println("Last accessed date/time: " + data.lastAccessTime());
        println("Unique file identifier (iv available):" + data.fileKey());
    }
}
