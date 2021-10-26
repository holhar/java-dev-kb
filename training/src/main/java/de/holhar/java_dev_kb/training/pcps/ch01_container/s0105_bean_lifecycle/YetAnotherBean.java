package de.holhar.java_dev_kb.training.pcps.ch01_container.s0105_bean_lifecycle;

import de.holhar.java_dev_kb.training.pcps.ch01_container.s0104_application_context.config.SomeBeanOne;
import de.holhar.java_dev_kb.training.pcps.ch01_container.s0104_application_context.config.SomeBeanTwo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
 *
 * The bean starts up as a class that is annotated with a stereotype annotation (e.g. @Component or @Service) or is
 * instantiated in a method annotated with @Bean from a configuration class.
 */
@Component
public class YetAnotherBean implements InitializingBean, DisposableBean {

    private static final Logger logger = LoggerFactory.getLogger(YetAnotherBean.class);

    private SomeBeanOne someBeanOne;
    private SomeBeanTwo someBeanTwo;

    public YetAnotherBean() {
        logger.info("Stage 4: Calling the constructor.");
    }

    @Autowired
    public void setSomeBeanOne(SomeBeanOne someBeanOne) {
        logger.info("Stage 5: Calling the setter.");
        this.someBeanOne = someBeanOne;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("Stage 6: Calling afterPropertiesSet.");
        long ct = System.currentTimeMillis();
        if (ct % 2 == 0) {
            someBeanTwo = new SomeBeanTwo();
        }
    }

    @Override
    public void destroy() {
        logger.info("Stage 9: Calling destroy method.");
    }

    /**
     * Yet another alternative: Also configurable by setting 'initMethod' attribute in @Bean annotation
     * {@link BeanConfiguration}
     */
    @PostConstruct
    private void initMethod() {
        logger.info("Stage 6 alternative: Calling init method.");
        long ct = System.currentTimeMillis();
        if (ct % 2 == 0) {
            someBeanTwo = new SomeBeanTwo();
        }
    }

    /**
     * Yet another alternative: Also configurable by setting 'destroyMethod' attribute in @Bean annotation
     * {@link BeanConfiguration}
     */
    @PreDestroy
    private void tearDown() {
        logger.info("Stage 9 alternative: Calling destroy method.");
    }
}
