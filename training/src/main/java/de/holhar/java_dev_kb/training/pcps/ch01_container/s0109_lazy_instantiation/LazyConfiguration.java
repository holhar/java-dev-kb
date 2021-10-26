package de.holhar.java_dev_kb.training.pcps.ch01_container.s0109_lazy_instantiation;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * Indicates whether a bean is to be lazily initialized.
 * May be used on any class directly or indirectly annotated with @Component or on methods annotated with @Bean.
 *
 * If this annotation is not present on a @Component or @Bean definition, eager initialization will occur.
 *
 * If present and set to true, the @Bean or @Component will not be initialized until referenced by another bean or
 * explicitly retrieved from the enclosing BeanFactory. If present and set to false, the bean will be instantiated on
 * startup by bean factories that perform eager initialization of singletons.
 *
 * If Lazy is present on a @Configuration class, this indicates that all @Bean methods within that @Configuration
 * should be lazily initialized. If @Lazy is present and false on a @Bean method within a @Lazy-annotated
 * @Configuration class, this indicates overriding the 'default lazy' behavior and that the bean should be eagerly
 * initialized.
 */
@Lazy(value = true)
@Configuration
public class LazyConfiguration {

    @Lazy(value = false)
    public LazyComponent lazyComponent() {
        return new LazyComponent();
    }
}
