package de.holhar.java_dev_kb.training.concurrency.ch05_building_blocks.s6_building_a_cache;

import java.math.BigInteger;

public class ExpensiveFunction implements Computable<String, BigInteger> {
    public BigInteger compute(String arg) {
        // after deep thought...
        return new BigInteger(arg);
    }
}
