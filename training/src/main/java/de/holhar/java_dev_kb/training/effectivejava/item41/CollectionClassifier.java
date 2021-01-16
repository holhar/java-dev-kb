package de.holhar.java_dev_kb.training.effectivejava.item41;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.*;

/**
 * Broken! What does this program print?
 */
public class CollectionClassifier {

    private static final Logger LOGGER = LoggerFactory.getLogger(CollectionClassifier.class);

    public static String classify(Set<?> s) {
        return "Set";
    }

    public static String classify(List<?> lst) {
        return "List";
    }

    public static String classify(Collection<?> c) {
        return "Unknown Collection";
    }

    public static void main(String[] args) {
        Collection<?>[] collections = {
                new HashSet<String>(),
                new ArrayList<BigInteger>(),
                new HashMap<String, String>().values()
        };

        for (Collection<?> c : collections) {
            LOGGER.debug(classify(c));
        }
    }
}
