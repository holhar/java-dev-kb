package de.holhar.java_dev_kb.training.ocp8.ch09_nio.sec03_file_attributes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.UserPrincipal;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.getNioHome;
import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class ManuallyDealWithBasicFileAttributes {

    private static final Logger LOGGER = LoggerFactory.getLogger(ManuallyDealWithBasicFileAttributes.class);

    public static void main(String[] args) {
        // Reading common attributes with isDirectory(), isRegularFile(), and isSymbolicLink()
        println(Files.isDirectory(Paths.get(getNioHome() + "here/tail.data")));
        println(Files.isRegularFile(Paths.get(getNioHome() + "animals/sharks.txt")));
        println(Files.isSymbolicLink(Paths.get(getNioHome() + "interacting-with-paths-and-files/food.source")));
        println("");

        // Checking file visibility with isHidden()
        try {
            println(Files.isHidden(Paths.get(getNioHome() + "animals/gorillas.txt")));
        } catch (IOException e) {
            LOGGER.error("Could not properly read file attributes '{}'", e.getMessage());
        }
        println("");

        // Testing file accessibility with isReadable() and isExecutable()
        println(Files.isReadable(Paths.get(getNioHome() + "here/tail.data")));
        println(Files.isExecutable(Paths.get(getNioHome() + "here/tail.data")));
        println("");

        // Reading file length with size()
        try {
            println(Files.size(Paths.get(getNioHome() + "animals/gopher.txt")));
        } catch (IOException e) {
            LOGGER.error("Could not determine file size '{}'", e.getMessage());
        }
        println("");

        // Managing file modifications with getLastModifiedTime() and setLastModifiedTime()
        try {
            final Path path1 = Paths.get(getNioHome() + "animals/sharks.txt");
            println(Files.getLastModifiedTime(path1).toMillis());
            Files.setLastModifiedTime(path1, FileTime.fromMillis(System.currentTimeMillis()));
            println(Files.getLastModifiedTime(path1).toMillis());
        } catch (IOException e) {
            LOGGER.error("Could not get or set lastModifiedTime '{}'", e.getMessage());
        }
        println("");

        /*
         * Managing ownership with getOwner() and setOwner():
         *
         * In order to set a file owner to an arbitrary user, the NIO.2 API provides a UserPrincipalLookupService
         * helper class for finding a UserPrincipal record for a particular user within a file system:
         */
        try {
            // 1. Version: Get UserPrincipal via FileSystems helper class:
            UserPrincipal owner1 = FileSystems.getDefault().getUserPrincipalLookupService().lookupPrincipalByName("holger");
            println("owner1: " + owner1.getName());

            // 2. Version: Get UserPrincipal via path instance:
            Path path1 = Paths.get(getNioHome() + "animals/gorillas.txt");
            UserPrincipal owner2 = path1.getFileSystem().getUserPrincipalLookupService().lookupPrincipalByGroupName("holger");
            println("owner2: " + owner2.getName());

            // Examples of the getOwner() and setOwner() methods:
            // Read owner of file
            println(Files.getOwner(path1).getName());

            // Change owner of file
            Files.setOwner(path1, owner1);

            // Output the updated owner information
            println(Files.getOwner(path1).getName());
        } catch (IOException e) {
            LOGGER.error("Could not retrieve UserPrincipal nor get or set file ownership '{}'", e.getMessage());
        }
    }
}
