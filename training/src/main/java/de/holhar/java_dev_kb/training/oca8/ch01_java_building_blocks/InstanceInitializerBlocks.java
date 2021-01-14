package de.holhar.java_dev_kb.training.oca8.ch01_java_building_blocks;

class Chick {
    private String name = "Fluffy";

    // Order matters for the fields and blocks of code => DOES NOT COMPILE:
    {
        // System.out.println(name);
    }

    // An instance initializer block
    // Fields and instance initializer blocks are run in the order in which they appear in the file.
    {
        System.out.println("setting field");
    }

    {
        System.out.println("Another instance initializer");
    }

    // The constructor runs after all fields and instance initializer blocks have run.
    public Chick() {
        name = "Tiny";
        System.out.println("setting constructor");
    }

    public static void main(String[] args) {
        Chick chick = new Chick();
        System.out.println(chick.name);
    }
}
