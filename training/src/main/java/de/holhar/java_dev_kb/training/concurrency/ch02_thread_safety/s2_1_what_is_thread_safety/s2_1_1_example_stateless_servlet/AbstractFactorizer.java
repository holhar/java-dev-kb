package de.holhar.java_dev_kb.training.concurrency.ch02_thread_safety.s2_1_what_is_thread_safety.s2_1_1_example_stateless_servlet;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.math.BigInteger;

public abstract class AbstractFactorizer {

    protected BigInteger extractFromRequest(ServletRequest request) {
        return null;
    }

    protected BigInteger[] factor(BigInteger i) {
        return null;
    }

    protected void encodeIntoResponse(ServletResponse response, BigInteger[] factors) {

    }
}
