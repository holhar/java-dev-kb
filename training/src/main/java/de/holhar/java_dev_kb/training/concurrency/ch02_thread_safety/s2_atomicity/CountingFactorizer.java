package de.holhar.java_dev_kb.training.concurrency.ch02_thread_safety.s2_atomicity;

import de.holhar.java_dev_kb.training.concurrency.ch02_thread_safety.AbstractFactorizer;
import de.holhar.java_dev_kb.training.concurrency.utils.ThreadSafe;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Servlet that counts requests using AtomicLong.
 */
@ThreadSafe
public class CountingFactorizer extends AbstractFactorizer implements Servlet {

    private final AtomicLong count = new AtomicLong(0);

    public long getCount() {
        return count.get();
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        BigInteger i = extractFromRequest(request);
        BigInteger[] factors = factor(i);
        count.incrementAndGet(); // <-- thread-safe atomic operation
        encodeIntoResponse(response, factors);
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
