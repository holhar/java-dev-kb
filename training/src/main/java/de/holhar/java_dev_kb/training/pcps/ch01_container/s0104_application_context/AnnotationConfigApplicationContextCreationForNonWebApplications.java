package de.holhar.java_dev_kb.training.pcps.ch01_container.s0104_application_context;

import de.holhar.java_dev_kb.training.pcps.ch01_container.s0104_application_context.config.SomeBeanOne;
import de.holhar.java_dev_kb.training.pcps.ch01_container.s0104_application_context.config.SomeBeanTwo;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * AnnotationConfigApplicationContext is used with standalone applications that use Java configuration, annotated
 * with @Configuration (see {@link de.holhar.java_dev_kb.training.pcps.ch01_container.s0104_application_context.config.ApplicationConfiguration}
 * for an example).
 */
@Configuration
public class AnnotationConfigApplicationContextCreationForNonWebApplications {

    private AnnotationConfigApplicationContext emptyContext;
    private AnnotationConfigApplicationContext beanFactoryContext;
    private AnnotationConfigApplicationContext annotatedClassesContext;
    private AnnotationConfigApplicationContext basePackageContext;

    public void ApplicationContextCreationOptions() {

        /*
         * Create empty ApplicationContext
         */
        emptyContext = new AnnotationConfigApplicationContext();

        /*
         * Init ApplicationContext by given BeanFactory (might be empty in case the BeanFactory does not supply any
         * beans)
         */
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactoryContext = new AnnotationConfigApplicationContext(beanFactory);

        /*
         * Init ApplicationContext by given AnnotatedClasses collection
         */
        annotatedClassesContext = new AnnotationConfigApplicationContext(SomeBeanOne.class, SomeBeanTwo.class);

        /*
         * Init ApplicationContext by supplied package reference (including sub-packages). The context will be
         * populated with the beans from @Configuration-annotated classes within the packages.
         */
        basePackageContext = new AnnotationConfigApplicationContext("de.holhar.java_dev_kb.training.pcps.ch01_container.s0104_application_context.config");
    }
}
