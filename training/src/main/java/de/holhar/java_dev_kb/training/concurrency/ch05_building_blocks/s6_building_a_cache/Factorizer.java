package de.holhar.java_dev_kb.training.concurrency.ch05_building_blocks.s6_building_a_cache;

import de.holhar.java_dev_kb.training.concurrency.ch02_thread_safety.AbstractFactorizer;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.math.BigInteger;

/**
 * Factorizing servlet that caches results using Memoizer4.
 */
public class Factorizer extends AbstractFactorizer implements Servlet {

    private final Computable<BigInteger, BigInteger[]> c = this::factor;
    private final Computable<BigInteger, BigInteger[]> cache = new Memoizer4<>(c);

    public void service(ServletRequest request, ServletResponse response) {
        try {
            BigInteger i = extractFromRequest(request);
            encodeIntoResponse(response, cache.compute(i));
        } catch (InterruptedException e) {
            encodeError(response, "factorization interrupted");
        }
    }

    private void encodeError(ServletResponse response, String errorMessage) {
        // do stuff...
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
