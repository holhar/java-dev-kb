package de.holhar.java_dev_kb.training.effectivejava.item33;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Using an EnumMap to associate data with an enum
 */
class HerbClientEnumSet {

    private static final Logger LOGGER = LoggerFactory.getLogger(HerbClientEnumSet.class);

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

        Map<Herb.Type, Set<Herb>> herbsByType = new EnumMap<>(Herb.Type.class);

        for (Herb.Type t : Herb.Type.values()) {
            herbsByType.put(t, new HashSet<>());
        }

        for (Herb h : garden) {
            herbsByType.get(h.type).add(h);
        }

        LOGGER.debug("{}", herbsByType);
    }
}
