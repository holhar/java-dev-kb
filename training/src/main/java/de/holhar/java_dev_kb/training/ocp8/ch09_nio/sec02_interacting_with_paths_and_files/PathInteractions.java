package de.holhar.java_dev_kb.training.ocp8.ch09_nio.sec02_interacting_with_paths_and_files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.getNioHome;
import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class PathInteractions {

    private static final Logger LOGGER = LoggerFactory.getLogger(PathInteractions.class);

    public static void main(String[] args) {

        // Viewing the Path with toString(), getNameCount(), and getName()
        Path path1 = Paths.get("/land/hippo/harry.happy");
        println("The path name is: " + path1);

        for (int i = 0; i < path1.getNameCount(); i++) {
            println("    Element " + i + " is: " + path1.getName(i));
        }
        println("");

        // Accessing Path components with getFileName(), getParent(), and getRoot()
        printPathInformation(Paths.get("/zoo/armadillo/shells.txt"));
        println("");
        printPathInformation(Paths.get("armadillo/shells.txt"));
        println("");

        // Checking Path Type with isAbsolute() and toAbsolutePath()
        Path path2 = Paths.get("/birds/condor.txt");
        println("Path2 is absolute?: " + path2.isAbsolute());
        println("absolute Path2: " + path2.toAbsolutePath()); // Returns a new Path object!
        println("");

        Path path3 = Paths.get("birds/condor.txt");
        println("Path3 is absolute?: " + path3.isAbsolute());
        println("absolute Path3: " + path3.toAbsolutePath());
        println("");

        // Creating a new Path with subpath() - Watch the inclusive start index!
        Path path4 = Paths.get("/mammal/carnivore/raccoon.image");
        println("Path is: " + path4);
        println("Subpath from 0 to 3 is: " + path4.subpath(0, 3));
        println("Subpath from 1 to 3 is: " + path4.subpath(1, 3));
        println("Subpath from 1 to 2 is: " + path4.subpath(1, 2));
        //println("Subpath from 0 to 4 is: " + path4.subpath(0,4)); // THROWS EXCEPTION AT RUNTIME
        //println("Subpath from 1 to 2 is: " + path4.subpath(1,1)); // THROWS EXCEPTION AT RUNTIME
        println("");

        // Deriving a Path with relativize()
        Path path5 = Paths.get("fish.txt");
        Path path6 = Paths.get("birds.txt");
        println(path5.relativize(path6));
        println(path6.relativize(path5));
        println("");
        Path path7 = Paths.get("/habitat");
        Path path8 = Paths.get("/sanctuary/raven");
        println(path7.relativize(path8));
        println(path8.relativize(path7));
        println("");

        // Joining Path objects with resolve()
        final Path path9 = Paths.get("/cats/../panther");
        final Path path10 = Paths.get("food");
        Path path13 = path9.resolve(path10);
        println(path13); // Does not clean up path symbols
        println("");

        final Path path11 = Paths.get("/turkey/food");
        final Path path12 = Paths.get("/tiger/cage");
        println(path11.resolve(path12)); // returns the second absolute path if both paths are absolute
        println("");

        // Cleaning up a Path with normalize()
        println(path13.normalize());
        println("");

        // Checking for File existence with toRealPath - like toAbsolutePath, but checks for existence
        try {
            println(Paths.get(getNioHome() + "interacting-with-paths-and-files/food.source").toRealPath());
            println(Paths.get(getNioHome() + "interacting-with-paths-and-files/food.txt").toRealPath());

            // Use the toRealPath() method to gain access to the current working directory
            println(Paths.get(".").toRealPath());
        } catch (IOException e) {
            LOGGER.error("Could not create real Path '{}'", e.getMessage());
        }
    }

    private static void printPathInformation(Path path) {
        println("Filename is: " + path.getFileName());
        println("Root is: " + path.getRoot());

        Path currentPath = path;
        while ((currentPath = currentPath.getParent()) != null) {
            println("    Current parent is: " + currentPath);
        }
    }
}
