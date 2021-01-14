package de.holhar.java_dev_kb.training.ocp8.ch02_design_patterns_and_principles.sec05_design_patterns.subsec01_singleton;

/**
 * @author hhs@dasburo.com
 *
 * Simple Singleton
 */
public class HayStorage {

    private int quantity = 0;

    /*
     * Private constructor to ensure no instantiation from outside the class - this marks the class implicitly as final
     * (no possible child can call the constructor of this class via super())
     */
    private HayStorage() {}

    // The singleton is stored within a private static field inside the class
    private static final HayStorage instance = new HayStorage();

    // Accessor method to return the singleton to a caller if necessary
    public static HayStorage getInstance() {
        return instance;
    }

    public synchronized void addHay(int amount) {
        quantity += amount;
    }

    public synchronized boolean removeHay(int amount) {
        if (quantity < amount) {
            return false;
        }
        quantity -= amount;
        return true;
    }

    public synchronized int getHayQuantity() {
        return quantity;
    }
}
