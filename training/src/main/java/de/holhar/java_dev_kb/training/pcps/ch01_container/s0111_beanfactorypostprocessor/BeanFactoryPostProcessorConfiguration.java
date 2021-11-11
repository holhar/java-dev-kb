package de.holhar.java_dev_kb.training.pcps.ch01_container.s0111_beanfactorypostprocessor;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.annotation.Order;

@Configuration
public class BeanFactoryPostProcessorConfiguration {

    /**
     * Q1.11:
     * PropertySourcesPlaceholderConfigurer implements {@link org.springframework.beans.factory.config.BeanFactoryPostProcessor}.
     *
     * During the initialization process in the bean lifecycle, beans are further processed before initialization
     * ({@link de.holhar.java_dev_kb.training.pcps.ch01_container.s0112_beanpostprocessor.InitAndDestroyMethodsBean}, step 3).
     *
     * This processing is done by *factory post processors* that are automatically picked up by the application context,
     * created, and applied before any other beans are created. The ApplicationContext recognizes them by the
     * implemented interface {@link org.springframework.beans.factory.config.BeanFactoryPostProcessor}.
     *
     * A bean factory post processor may not create instances of beans, only modify bean meta-data. Bean factory post
     * processors are useful for changing bean definitions at runtime and to facilitate swapping different
     * stage-dependent values as well.
     *
     * PropertySourcesPlaceholderConfigurer is a specialization of PlaceholderConfigurerSupport that resolves ${...}
     * placeholders within bean definition property values and @Value annotations against the current Spring
     * Environment and its set of PropertySources.
     *
     * Note that the bean method is static, to make sure that the bean is instantiated before regular beans that
     * might be processed by the post processor.
     */
    @Bean
    @Order // defines the sort order for a post processor.
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
