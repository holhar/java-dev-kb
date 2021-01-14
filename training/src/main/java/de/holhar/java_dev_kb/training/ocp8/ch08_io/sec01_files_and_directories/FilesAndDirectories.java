package de.holhar.java_dev_kb.training.ocp8.ch08_io.sec01_files_and_directories;

import java.io.File;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class FilesAndDirectories {

    public static void main(String[] args) {
        // Retrieve file separator of underlying OS
        println(System.getProperty("file.separator"));
        println(File.separator);

        // Create File object and check if it exists
        File file = new File("path/to/file/zoo.txt");
        println("File exists?: " + file.exists());

        // Create File using parent reference
        File parent = new File("/home/smith");
        File child = new File(parent, "data/zoo.txt");
        println("");

        // Working with File objects
        File books = new File("src/main/resources/io-examples/buecher-liste.txt");
        printFileInformation(books);
        println("");

        File resourceFolder = new File("src/main/resources/");
        printFileInformation(resourceFolder);
    }

    private static void printFileInformation(File file) {
        println("File exists?: " + file.exists());

        if (file.exists()) {
            println("Absolute path: " + file.getAbsolutePath());
            println("Is directory: " + file.isDirectory());
            println("Parent path: " + file.getParent());

            if (file.isFile()) {
                println("File size: " + file.length());
                println("File LastModified: " + file.lastModified());
            } else {
                for (File subfile : file.listFiles()) {
                    println("\t" + subfile.getName());
                }
            }
        }
    }
}
