package de.holhar.java_dev_kb.training.pcps.ch01_container.s0114_autowired_s0115_qualifier_s0117_s0118_beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication(scanBasePackages = "de.holhar.java_dev_kb.training.pcps.ch01_container.s0114_autowired_s0115_qualifier_s0117_s0118_beans")
public class AutowiredApplication {

    private static final Logger logger = LoggerFactory.getLogger(AutowiredApplication.class);

    @Autowired
    private AutowiredComponent autowiredComponent;

    public static void main(String[] args) {
        SpringApplication.run(AutowiredApplication.class, args);
    }

    @PostConstruct
    private void init() {
        logger.info("Hello from {}", autowiredComponent.helloFromDependencies());
    }
}
