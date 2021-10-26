package de.holhar.java_dev_kb.training.pcps.ch01_container.s0112_beanpostprocessor;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanPostProcessorConfiguration {

    /**
     * AutowiredAnnotationBeanPostProcessor implements {@link org.springframework.beans.factory.config.BeanPostProcessor}.
     *
     * During the initialization process in the bean lifecycle, beans are further processed after dependency injection
     * ({@link InitAndDestroyMethodsBean}, step 6).
     *
     * BeanPostProcessor provides factory hooks that allows for custom modification of new bean
     * instances, e.g. checking for marker interfaces or wrapping them with proxies.
     *
     * ApplicationContexts can autodetect BeanPostProcessor beans in their bean definitions and apply them to any
     * beans subsequently created. Plain bean factories allow for programmatic registration of post-processors,
     * applying to all beans created through this factory.
     *
     * Typically, post-processors that populate beans via marker interfaces or the like will implement
     * postProcessBeforeInitialization, while post-processors that wrap beans with proxies will normally implement
     * postProcessAfterInitialization.
     *
     * AutowiredAnnotationBeanPostProcessor is a BeanPostProcessor implementation that autowires annotated fields,
     * setter methods and arbitrary config methods. Such members to be injected are detected through a Java 5
     * annotation: by default, Spring's @Autowired and @Value annotations.
     */
    @Bean
    public static AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor() {
        return new AutowiredAnnotationBeanPostProcessor();
    }

    /**
     * Init method option 3: provide initMethod/destroyMethod - THIRD init/destroy method to be called.
     */
    @Bean(initMethod = "initMethodAlternative", destroyMethod = "tearDownAlternative")
    public InitAndDestroyMethodsBean initAndDestroyMethodsBean() {
        return new InitAndDestroyMethodsBean();
    }
}
