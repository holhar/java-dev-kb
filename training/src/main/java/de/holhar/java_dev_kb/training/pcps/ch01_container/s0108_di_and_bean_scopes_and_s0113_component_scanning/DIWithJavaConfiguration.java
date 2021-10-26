package de.holhar.java_dev_kb.training.pcps.ch01_container.s0108_di_and_bean_scopes_and_s0113_component_scanning;

import de.holhar.java_dev_kb.training.pcps.ch01_container.s0104_application_context.config.ApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/*
 * Indicates that a class declares one or more @Bean methods and may be processed by the Spring container to generate
 *  bean definitions and service requests for those beans at runtime.
 */
@Configuration
/*
 * Configures component scanning directives for use with @Configuration classes. Provides support parallel with
 * Spring XML's <context:component-scan> element. Either basePackageClasses or basePackages (or its alias value) may
 * be specified to define specific packages to scan. If specific packages are not defined, scanning will occur from
 * the package of the class that declares this annotation.
 *
 * Note that the @Configuration-annotated class itself candidates to component scanning.
 */
@ComponentScan(
        basePackages = "de.holhar.java_dev_kb.training.pcps.ch01_container.s0101_di_in_general",
        basePackageClasses = ApplicationConfiguration.class, // <- define base package by class located in it
        excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*Repository"),
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyService.class)
)
public class DIWithJavaConfiguration {

    /*
     * Dependency beans are created first and injected through the constructor here
     */
    @Bean
    public BeanWithDependencies beanWithDependencies(Dependency1 dependency1, Dependency2 dependency2, Dependency3 dependency3) {
        final BeanWithDependencies beanWithDependencies = new BeanWithDependencies(dependency1);
        beanWithDependencies.setDependency2(dependency2);
        beanWithDependencies.setDependency3(dependency3);
        return beanWithDependencies;
    }

    @Bean
    public Dependency1 dependency1() {
        return new Dependency1();
    }

    @Bean
    public Dependency2 dependency2() {
        return new Dependency2();
    }

    @Bean
    public Dependency3 dependency3() {
        return new Dependency3();
    }
}
