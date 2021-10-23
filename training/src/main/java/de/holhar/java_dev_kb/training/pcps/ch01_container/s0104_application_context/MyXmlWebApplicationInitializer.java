package de.holhar.java_dev_kb.training.pcps.ch01_container.s0104_application_context;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * For configuration and initialization of Servlet 2 based Spring web applications see /webapp/WEB-INF/web.xml file
 * below the module root. In Servlet 3 standard the web.xml has become optional and a class implementing the
 * {@link org.springframework.web.WebApplicationInitializer} can be used to create a Spring application context.
 *
 * The following example shows the initialization of a Spring web Servlet 3 application based on XML configuration
 */
public class MyXmlWebApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {
        final XmlWebApplicationContext xmlWebApplicationContext = new XmlWebApplicationContext();
        xmlWebApplicationContext.setConfigLocation("/webapp/WEB-INF/applicationContext.xml");
        xmlWebApplicationContext.setServletContext(servletContext);
        xmlWebApplicationContext.refresh();
        xmlWebApplicationContext.start();

        /*
         * Create and register DispatcherServlet to enable the application to process web requests.
         */
        final DispatcherServlet dispatcherServlet = new DispatcherServlet(xmlWebApplicationContext);
        final ServletRegistration.Dynamic servletRegistration = servletContext.addServlet("app", dispatcherServlet);
        servletRegistration.setLoadOnStartup(1);
        servletRegistration.addMapping("/app/*");
    }
}
