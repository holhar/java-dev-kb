package de.holhar.java_dev_kb.training.pcps.ch01_container.s0112_beanpostprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Bean lifecycle in general:
 * INITIALIZATION
 * 1. ApplicationContext initialization
 * 2. Loading bean definitions (parse configuration classes and collect stereotype annotated classes)
 * 3. Bean definition processing (beans are further processed by other beans that implement BeanFactoryPostProcessor)
 * 4. Bean instantiation
 * 5. Dependency injection
 * 6. Bean processing (beans are further processed by implementing BeanPostProcessor)
 * 6.a. bean post process beans are invoked before initialization
 * 6.b. beans are initialized
 * 6.c. bean post process beans are invoked after initialization
 * USAGE
 * 7. Bean are used
 * DESTRUCTION
 * 8. ApplicationContext starts destruction process
 * 9. Beans are destroyed
 */
public class InitAndDestroyMethodsBean implements InitializingBean, DisposableBean {

    private static final Logger logger = LoggerFactory.getLogger(InitAndDestroyMethodsBean.class);

    public InitAndDestroyMethodsBean() {
        logger.info("Stage 4: Calling the constructor.");
    }

    /**
     * Init method option 1: implement {@link InitializingBean} and override afterPropertiesSet - SECOND init method
     * to be called.
     *
     * An initialization method is a method in a Spring bean that will be invoked by the Spring application
     * container after all properties on the bean have been populated but before the bean is taken into use.
     */
    @Override
    public void afterPropertiesSet() {
        logger.info("Stage 6: Calling afterPropertiesSet by implementing InitializingBean.");
    }

    /**
     * destroy method option 1: implement {@link DisposableBean} and override destroy - SECOND destroy method to be
     * called.
     *
     * A destroy method is a method in a Spring bean that will be invoked by the Spring application
     * container immediately before the bean is to be taken out of use, typically when the Spring
     * application context is about to be closed.
     */
    @Override
    public void destroy() {
        logger.info("Stage 9: Calling destroy method by implementing DisposableBean.");
    }

    /**
     * Init method option 2: annotate method with @PostConstruct (for Option 3 see
     * {@link BeanPostProcessorConfiguration}) - FIRST init method to be called.
     *
     * @PostConstruct-annotated methods get automatically registered for Spring application contexts that use
     * annotation-based configuration (e.g. {@link org.springframework.context.annotation.AnnotationConfigApplicationContext}),
     * as a default {@link org.springframework.context.annotation.CommonAnnotationBeanPostProcessor} gets registered.
     */
    @PostConstruct
    private void initMethod() {
        logger.info("Stage 6: Calling init method by @PostConstruct.");
    }

    private void initMethodAlternative() {
        logger.info("Stage 6 alternative 3: Calling another init method by declaring it as @Bean initMethod.");
    }

    /**
     * Destroy method option 2: annotate method with @PreDestroy (for Option 3 see
     * {@link BeanPostProcessorConfiguration}) - FIRST destroy method to be called.
     *
     * @PreDestroy-annotated methods get automatically registered for Spring application contexts that use
     * annotation-based configuration (e.g. {@link org.springframework.context.annotation.AnnotationConfigApplicationContext}),
     * as a default {@link org.springframework.context.annotation.CommonAnnotationBeanPostProcessor} gets registered.
     */
    @PreDestroy
    private void tearDown() {
        logger.info("Stage 9: Calling destroy method by @PreDestroy.");
    }

    private void tearDownAlternative() {
        logger.info("Stage 9 alternative 3: Calling another destroy method by declaring it as @Bean destroyMethod.");
    }
}
