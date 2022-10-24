package de.holhar.java_dev_kb.training.concurrency.ch03_sharing_objects.s3_5_safe_publication;

/**
 * Class at risk of failure if not properly published.
 */
public class Holder {

    private final int value;

    public Holder(int value) {
        this.value = value;
    }

    public void assertSanity() {
        if (value != value) {
            // This might actually happen!
            throw new AssertionError("This statement is false.");
        }
    }

    public int getValue() {
        return value;
    }
}
