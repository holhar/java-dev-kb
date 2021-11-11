package de.holhar.java_dev_kb.training.pcps.ch01_container.s0114_autowired_s0115_qualifier_s0117_s0118_beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AutowiredComponent {

    /**
     * Q1.14: @Autowired on field level.
     * Q1.15: Referencing the method name of the bean definition for DependencyThree as @Qualifier value.
     */
    @Autowired
    @Qualifier("deviatingBeanIdentifier")
    private DependencyOne dependencyOne;
    private DependencyTwo dependencyTwo;
    private DependencyThree dependencyThree;

    /**
     * Q1.14: @Autowired on constructor level.
     * Q1.15: Referencing the @Bean value used for {@link DependencyTwo} as @Qualifier value.
     */
    @Autowired
    public AutowiredComponent(@Qualifier("exoticBeanName") DependencyTwo dependencyTwo) {
        this.dependencyTwo = dependencyTwo;
    }

    /**
     * Q1.14: @Autowired on method level.
     * Q1.15: Referencing the @Component value used for {@link DependencyThree} as parameter name.
     */
    @Autowired
    public void setDependencyThree(DependencyThree awesomeBeanName) {
        this.dependencyThree = awesomeBeanName;
    }

    public String helloFromDependencies() {
        return dependencyOne.getName() + " " + dependencyTwo.getName() + " " + dependencyThree.getName();
    }
}
