package de.holhar.java_dev_kb.training.concurrency.ch02_thread_safety.s2_1_what_is_thread_safety.s2_1_1_example_stateless_servlet;

import de.holhar.java_dev_kb.training.concurrency.utils.ThreadSafe;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

/**
 * A stateless servlet.
 */
@ThreadSafe
public class StatelessFactorizer extends AbstractFactorizer implements Servlet {

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        BigInteger i = extractFromRequest(request);
        BigInteger[] factors = factor(i);
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
