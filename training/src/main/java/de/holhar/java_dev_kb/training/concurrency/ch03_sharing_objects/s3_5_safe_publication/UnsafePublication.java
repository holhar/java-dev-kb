package de.holhar.java_dev_kb.training.concurrency.ch03_sharing_objects.s3_5_safe_publication;

import de.holhar.java_dev_kb.training.concurrency.ch03_sharing_objects.s3_5_safe_publication.s3_5_1_improper_publication.Holder;

/**
 * Publishing an object without adequate synchronization.
 * Don't do this.
 */
public class UnsafePublication {

    public Holder holder;

    public void initialize() {
        holder = new Holder(42);
    }
}
