package de.holhar.java_dev_kb.training.effectivejava.item33;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * Using ordinal() to index an array - DON'T DO THIS!
 */
class HerbClientOrdinalIndexing {

    private static final Logger LOGGER = LoggerFactory.getLogger(HerbClientOrdinalIndexing.class);

    public static void main(String[] args) {

        Herb[] garden = new Herb[]{
                new Herb("garden1", Herb.Type.ANNUAL),
                new Herb("garden2", Herb.Type.ANNUAL),
                new Herb("garden3", Herb.Type.PERENNIAL),
                new Herb("garden4", Herb.Type.BIENNIAL),
                new Herb("garden5", Herb.Type.PERENNIAL),
                new Herb("garden6", Herb.Type.PERENNIAL),
                new Herb("garden7", Herb.Type.BIENNIAL),
        };

        // Array of sets indexed by Herb.Type.ordinal()
        Set<Herb>[] herbsByType = (Set<Herb>[]) new Set[Herb.Type.values().length];

        for (int i = 0; i < herbsByType.length; i++) {
            herbsByType[i] = new HashSet<Herb>();
        }

        for (Herb h : garden) {
            herbsByType[h.type.ordinal()].add(h);
        }

        // Print the results
        for (int i = 0; i < herbsByType.length; i++) {
            LOGGER.debug("{}: {}\n", Herb.Type.values()[i], herbsByType[i]);
        }
    }
}
