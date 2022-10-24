package de.holhar.java_dev_kb.training.concurrency.ch05_building_blocks.s5_1_synchronized_collections;

import de.holhar.java_dev_kb.training.concurrency.utils.GuardedBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Iteration hidden within string concatenation.
 * Don't do this.
 */
public class HiddenIterator {

    private static final Logger LOGGER = LoggerFactory.getLogger(HiddenIterator.class);

    @GuardedBy("this")
    private final Set<Integer> set = new HashSet<>();

    public synchronized void add(Integer i) {
        set.add(i);
    }

    public synchronized void remove(Integer i) {
        set.remove(i);
    }

    public void addTenThings() {
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            add(r.nextInt());
        }
        LOGGER.debug("Added ten elements to {}", set);
    }
}
