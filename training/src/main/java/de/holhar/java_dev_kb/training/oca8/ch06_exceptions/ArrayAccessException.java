package de.holhar.java_dev_kb.training.oca8.ch06_exceptions;

import static de.holhar.java_dev_kb.training.oca8.util.Oca8Utils.print;

public class ArrayAccessException {
    public static void main(String args[]) {
        String[] students = {"Shreya", "Joseph", null};
        // Throws unchecked Exception ArrayIndexOutOfBoundsException
        print(students[5].length());
    }
}
