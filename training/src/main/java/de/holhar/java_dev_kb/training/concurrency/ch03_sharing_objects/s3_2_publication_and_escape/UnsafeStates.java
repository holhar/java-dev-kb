package de.holhar.java_dev_kb.training.concurrency.ch03_sharing_objects.s3_2_publication_and_escape;

/**
 * Allowing internal mutable state to escape.
 * Don't do this.
 */
public class UnsafeStates {

    private String[] states = new String[] {
            "AK", "Al", "..."
    };

    public String[] getStates() {
        return states;
    }
}
