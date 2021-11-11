package de.holhar.java_dev_kb.training.pcps.ch01_container.s0110_property_source;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Q1.10:
 * A property source represents a source of key-value pairs.
 * Valid sources are:
 * - system props of JVM
 * - system env vars
 * - props in a JNDI env
 * - servlet conf init params
 * - servlet context init params
 * - properties files
 *
 * @PropertySource-annotation provides a convenient and declarative mechanism for adding a PropertySource to Spring's
 * Environment. To be used in conjunction with @Configuration classes.
 */
@Configuration
@PropertySource("classpath:propertysource.properties")
public class PropertySourceConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(PropertySourceConfiguration.class);

    @Bean
    public BeanWithPropertySource beanWithPropertySource(@Value("${app.name}") String name) {
        final BeanWithPropertySource beanWithPropertySource = new BeanWithPropertySource(name);
        logger.info("beanWithPropertySource - name: '{}'", beanWithPropertySource.getName());
        return beanWithPropertySource;
    }
}
