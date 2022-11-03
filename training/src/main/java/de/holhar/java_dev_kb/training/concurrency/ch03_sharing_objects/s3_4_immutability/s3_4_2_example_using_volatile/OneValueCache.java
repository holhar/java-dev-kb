package de.holhar.java_dev_kb.training.concurrency.ch03_sharing_objects.s3_4_immutability.s3_4_2_example_using_volatile;

import de.holhar.java_dev_kb.training.concurrency.utils.Immutable;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * Immutable holder for caching a number and its factors.
 */
@Immutable
public class OneValueCache {

    private final BigInteger lastNumber;
    private final BigInteger[] lastFactors;

    public OneValueCache(BigInteger lastNumber, BigInteger[] lastFactors) {
        this.lastNumber = lastNumber;
        this.lastFactors = Arrays.copyOf(lastFactors, lastFactors.length);
    }

    public BigInteger[] getFactors(BigInteger i) {
        if (lastNumber == null || !lastNumber.equals(i)) {
            return null;
        } else {
            return Arrays.copyOf(lastFactors, lastFactors.length);
        }
    }
}
