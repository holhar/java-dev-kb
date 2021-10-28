package de.holhar.java_dev_kb.training.pcps.ch01_container.s0114_autowired_s0115_qualifier_s0117_s0118_beans;

import org.springframework.stereotype.Component;

@Component("awesomeBeanName")
public class DependencyThree {

    private final String name;

    public DependencyThree() {
        name = "DependencyThree";
    }

    public String getName() {
        return name;
    }
}
