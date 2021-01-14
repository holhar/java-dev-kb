package de.holhar.java_dev_kb.training.oca8.ch06_exceptions;

public class RecursiveMethodAccess {

    public static void main(String[] args) {
        // Throws unchecked error StackOverflowError
        myMethod();
    }

    private static void myMethod() {
        System.out.println("myMethod");
        myMethod();
    }
}
