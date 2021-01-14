package de.holhar.java_dev_kb.training.oca8.ch01_java_building_blocks;

// SECOND IMPORT DOES NOT COMPILE:
// import java.util.*;
// import java.sql.*; // both packages contain a Date class

// COMPILES (explicit imports take precedence over wildcard imports):

public class ImportConflicts {
    public static void main(String[] args) {
        System.out.println("It works!");
    }
}
