/*
 * Comments can be added even before the package declaration.
 */
package de.holhar.java_dev_kb.training.oca8.ch01_java_building_blocks;

// Not using 'public' keyword here as then file name and class name must match
class Animal {
    String name;

    // Different possibilities to provide the 'args' parameter:
    // public static void main(String[] args) {
    // public static void main(String args[]) {
    public static void main(String... args) {
        Animal animal = new Animal();
        animal.setName(args[0]);
        System.out.print("The animals name is " + animal.getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }
}

// Defining a second class in the same file
class Animal2 {

}
