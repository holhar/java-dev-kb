package de.holhar.java_dev_kb.training.pcps.ch01_container.s0114_autowired_s0115_qualifier_s0117_s0118_beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutowiredConfiguration {

    /**
     * @Bean indicates that a method produces a bean to be managed by the Spring container.
     * initMethod and destroyMethod are configurable within @Bean annotation
     *
     * Bean Names - While a name attribute is available, the default strategy for determining the name of a bean is to
     * use the name of the @Bean method. This is convenient and intuitive, but if explicit naming is desired, the
     * name attribute (or its alias value) may be used. Also note that name accepts an array of Strings, allowing for
     * multiple names (i.e. a primary bean name plus one or more aliases) for a single bean.
     *
     * Profile, Scope, Lazy, DependsOn, Primary, Order - Note that the @Bean annotation does not provide attributes for
     * profile, scope, lazy, depends-on or primary. Rather, it should be used in conjunction with @Scope, @Lazy,
     * @DependsOn and @Primary annotations to declare those semantics.
     *
     * By marking this method as static, it can be invoked without causing instantiation of its declaring
     * @Configuration class, thus avoiding the above-mentioned lifecycle conflicts. Note however that static @Bean
     * methods will not be enhanced for scoping and AOP semantics as mentioned above. This works out in BFPP cases,
     * as they are not typically referenced by other @Bean methods. As a reminder, a WARN-level log message will be
     * issued for any non-static @Bean methods having a return type assignable to BeanFactoryPostProcessor.
     */
    @Bean
    public DependencyOne deviatingBeanIdentifier() {
        return new DependencyOne("deviatingBeanIdentifier");
    }

    @Bean
    public DependencyOne dependencyOne() {
        return new DependencyOne("dependencyOne");
    }

    @Bean("exoticBeanName")
    public DependencyTwo bla() {
        return new DependencyTwo("exoticBeanName");
    }

    @Bean
    public DependencyTwo DependencyTwo() {
        return new DependencyTwo("exoticBeanName");
    }
}
