package de.holhar.java_dev_kb.training.concurrency.ch02_thread_safety.s2_2_atomicity;

import de.holhar.java_dev_kb.training.concurrency.ch02_thread_safety.AbstractFactorizer;
import de.holhar.java_dev_kb.training.concurrency.utils.NotThreadSafe;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

/**
 * Servlet that counts requests without the necessary synchronization.
 * Don't do this.
 */
@NotThreadSafe
public class UnsafeCountingFactorizer extends AbstractFactorizer implements Servlet {

    private long count = 0;

    public long getCount() {
        return count;
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        BigInteger i = extractFromRequest(request);
        BigInteger[] factors = factor(i);
        ++count; // <-- non-atomic operation
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
