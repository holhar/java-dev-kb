package de.holhar.java_dev_kb.training.concurrency.ch03_sharing_objects.s3_2_publication_and_escape;

import java.util.HashSet;
import java.util.Set;

/**
 * Publishing an object.
 */
public class ObjectPublication {

    public static Set<Secret> knownSecrets;

    public void initialize() {
        knownSecrets = new HashSet<>();
    }

    public static class Secret {}
}
