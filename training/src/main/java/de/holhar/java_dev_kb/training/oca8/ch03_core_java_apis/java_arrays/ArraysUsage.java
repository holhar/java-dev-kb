package de.holhar.java_dev_kb.training.oca8.ch03_core_java_apis.java_arrays;

import static de.holhar.java_dev_kb.training.oca8.util.Oca8Utils.print;

public class ArraysUsage {

    public static void main(String[] args) {

        print("Arrays");
        print("======");
        print("");

        print("Arrays of Primitives");
        print("--------------------");
        print("");

        // Basic declaration
        int[] numbers1 = new int[3];

        // Square brackets can be written before or after the identifier
        int[] numAnimals1;
        int[] numAnimals2;
        int numAnimals3[];
        int numAnimals4[];

        // Declare and initialize in one line
        int[] numbers2 = new int[]{42, 55, 99};

        for (int number : numbers2) {
            print(number);
        }

        // Valid alternative
        int[] numbers3 = {99, 42, 55};

        // Creates two arrays
        int[] ids, types;

        // Creates one array and one int
        int ids2[], types2;

        print("Arrays with Reference Variables");
        print("-------------------------------");
        print("");

        String[] bugs = {"cricket", "beetle", "ladybug"};
        String[] alias = bugs;
        // true - both reference the same object (does not check the elements)
        print(bugs.equals(alias));

    }
}
