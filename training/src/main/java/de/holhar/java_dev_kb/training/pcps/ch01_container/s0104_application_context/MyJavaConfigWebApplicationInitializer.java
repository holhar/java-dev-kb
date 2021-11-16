package de.holhar.java_dev_kb.training.pcps.ch01_container.s0104_application_context;

import de.holhar.java_dev_kb.training.pcps.ch01_container.s0104_application_context.config.ApplicationConfiguration;
import de.holhar.java_dev_kb.training.pcps.ch01_container.s0108_di_and_bean_scopes_and_s0113_component_scanning.ScopesOfSpringBeans;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

/**
 * Q1.4:
 * For configuration and initialization of Servlet 2 based Spring web applications see /webapp/WEB-INF/web.xml file
 * below the module root. In Servlet 3 standard the web.xml has become optional and a class implementing the
 * {@link org.springframework.web.WebApplicationInitializer} can be used to create a Spring application context.
 *
 * Q5.0.c:
 * Web application context, specified by the WebApplicationContext interface, is a Spring application context for a
 * web applications. It has all the properties of a regular Spring application context, given that the
 * WebApplicationContext interface extends the ApplicationContext interface, and add a method for retrieving the
 * standard Servlet API ServletContext for the web application.
 *
 * In addition to the standard Spring bean scopes singleton and prototype, there are three additional scopes
 * available in a web application context (see also {@link ScopesOfSpringBeans}):
 * - request - Single bean instance per HTTP request.
 * - session - Single bean instance per HTTP session.
 * - application - Single bean instance per ServletContext.
 *
 * The following example shows the initialization of a Spring web Servlet 3 application based on Java configuration
 */
public class MyJavaConfigWebApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {
        final AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext();
        annotationConfigWebApplicationContext.setServletContext(servletContext);
        annotationConfigWebApplicationContext.register(ApplicationConfiguration.class);
        annotationConfigWebApplicationContext.refresh();

        /**
         * Create and register DispatcherServlet to enable the application to process web requests.
         *
         * Q5.0.b:
         * The DispatcherServlet is a servlet that implements the front controller design pattern and fulfills the
         * following functions:
         * - Receives requests and delegates them to registered handlers.
         * - Resolves views by mapping view-names to View instances.
         * - Resolves exceptions that occur during handler mapping or execution.
         *
         * A Spring web application may define multiple dispatcher servlets, each of which has its own
         * namespace, its own Spring application context and its own set of mappings and handlers.
         */
        final DispatcherServlet dispatcherServlet = new DispatcherServlet(annotationConfigWebApplicationContext);
        final ServletRegistration.Dynamic servletRegistration = servletContext.addServlet("app2", dispatcherServlet);
        servletRegistration.setLoadOnStartup(1);
        servletRegistration.addMapping("/app-java/*");
    }
}
