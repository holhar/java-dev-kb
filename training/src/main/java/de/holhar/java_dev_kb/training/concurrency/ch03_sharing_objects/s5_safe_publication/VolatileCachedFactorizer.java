package de.holhar.java_dev_kb.training.concurrency.ch03_sharing_objects.s5_safe_publication;

import de.holhar.java_dev_kb.training.concurrency.ch02_thread_safety.AbstractFactorizer;
import de.holhar.java_dev_kb.training.concurrency.ch03_sharing_objects.s4_immutability.OneValueCache;
import de.holhar.java_dev_kb.training.concurrency.utils.ThreadSafe;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.math.BigInteger;

/**
 * Caching the last result using a volatile reference to an immutable holder object.
 */
@ThreadSafe
public class VolatileCachedFactorizer extends AbstractFactorizer implements Servlet {

    private volatile OneValueCache cache = new OneValueCache(null, null);

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        BigInteger i = extractFromRequest(request);
        BigInteger[] factors = cache.getFactors(i);
        if (factors == null) {
            factors = factor(i);
            cache = new OneValueCache(i, factors);
        }
        encodeIntoResponse(response, factors);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {

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
