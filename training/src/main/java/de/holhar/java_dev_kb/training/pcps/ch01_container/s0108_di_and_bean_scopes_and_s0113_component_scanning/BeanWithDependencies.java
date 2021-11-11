package de.holhar.java_dev_kb.training.pcps.ch01_container.s0108_di_and_bean_scopes_and_s0113_component_scanning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Q1.8:
 * DI is a two-step process:
 * 1. dependency definition through either constructor arguments, properties by setters, parameters to a factory method
 * 2. dependency injection through Spring container
 *
 * A class representing a Spring bean can be annotated with Spring annotations, in order to facilitate dependency
 * injection.
 *
 * @Component Indicates that an annotated class is a "component". Such classes are considered as candidates for
 * auto-detection when using annotation-based configuration and classpath scanning.
 */
@Component
public class BeanWithDependencies {

    private Dependency1 dependency1;
    private Dependency2 dependency2;

    /*
     * @Autowired marks a constructor, field, parameter, setter method or config method as to be autowired by Spring's
     * dependency injection facilities. This is an alternative to the JSR-330 javax.inject.Inject annotation, adding
     * required-vs-optional semantics.
     */
    @Autowired
    private Dependency3 dependency3;

    /*
     * Annotating the constructor with @Autowired is in fact not necessary for a bean class, as long as there is only
     *  one constructor present.
     */
    @Autowired
    public BeanWithDependencies(Dependency1 dependency1) {
        this.dependency1 = dependency1;
    }

    @Autowired
    public void setDependency2(Dependency2 dependency2) {
        this.dependency2 = dependency2;
    }

    public void setDependency3(Dependency3 dependency3) {
        this.dependency3 = dependency3;
    }
}
