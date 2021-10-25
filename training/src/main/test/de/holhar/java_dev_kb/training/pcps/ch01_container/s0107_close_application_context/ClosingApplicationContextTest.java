package de.holhar.java_dev_kb.training.pcps.ch01_container.s0107_close_application_context;

import de.holhar.java_dev_kb.training.effectivejava.item35.Test;
import de.holhar.java_dev_kb.training.pcps.ch01_container.TestConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ClosingApplicationContextTest {

    /**
     * The test method illustrates the mechanism involved for closing the application context for non-web
     * applications. For Spring web applications the ContextLoaderListener takes care of it by receiving a
     * ServletContextEvent when the container stops the web application.
     *
     * For Spring Boot the situation is the same for both, web and non-web applications.
     */
    @Test
    public void testClosingApplicationContext() {
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(TestConfiguration.class);

        // Causes the context to be closes when JVM is shutdown normally (recommended way for non-web applications)
        ctx.registerShutdownHook();

        // Close context immediately
        ctx.close();
    }
}
