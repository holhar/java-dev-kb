package de.holhar.java_dev_kb.training.concurrency.ch02_thread_safety.s3_locking;

import de.holhar.java_dev_kb.training.concurrency.ch02_thread_safety.AbstractFactorizer;
import de.holhar.java_dev_kb.training.concurrency.utils.NotThreadSafe;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Servlet that attempts to cache its last result without adequate atomicity,
 * Don't do this.
 */
@NotThreadSafe
public class UnsafeCachingFactorizer extends AbstractFactorizer implements Servlet {

    private final AtomicReference<BigInteger> lastNumber = new AtomicReference<>();
    private final AtomicReference<BigInteger[]> lastFactors = new AtomicReference<>();

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        BigInteger i = extractFromRequest(request);
        if (i.equals(lastNumber.get())) {
            encodeIntoResponse(response, lastFactors.get());
        } else {
            BigInteger[] factors = factor(i);
            lastNumber.set(i);
            lastFactors.set(factors);
            encodeIntoResponse(response, factors);
        }
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
