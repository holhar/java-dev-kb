package de.holhar.java_dev_kb.training.ocp8.ch01_advanced_class_design.sec05_coding_equals_hashcode_tostring;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

/**
 * @author hhs@dasburo.com
 */
public class Hippo {
    private String name;
    private int id;
    private int weight;

    public Hippo(String name, int id, int weight) {
        this.name = name;
        this.id = id;
        this.weight = weight;
    }

    public static void main(String[] args) {
        Hippo h1 = new Hippo("Harry", 12, 3100);
        println(h1);
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Id: " + id + ", Weight: " + weight;
    }

    // Has to accept Object for a proper @Override
    @Override
    public boolean equals(Object o) {
        // Has to return false for null values and objects that are not of Hippo type
        if (!(o instanceof Hippo)) return false;
        // Apply cast
        Hippo other = (Hippo) o;
        // choose proper values to determine if two objects are the same
        return other.name.equals(this.name)
                && other.id == this.id
                && other.weight == this.weight;
    }

    /*
     * - Within the same program, the result of hashCode() must not change, i.e. the values used
     *   for calculating the has code must not change
     * - If equals returns 'true' for two objects, hashCode must return the same result for these
     *   two objects, i.e. hashCode only has to use a subset of the variables used for equals
     * - If equals returns 'false' for two objects, it DOES NOT mean that hashCode has to return
     *   different values for these two objects, i.e. hashCode results do NOT need to be unique
     *   for unequal objects
     */
    @Override
    public int hashCode() {
        return this.id * 7;
    }
}
