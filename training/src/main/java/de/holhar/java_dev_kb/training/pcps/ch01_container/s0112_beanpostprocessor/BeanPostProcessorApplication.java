package de.holhar.java_dev_kb.training.pcps.ch01_container.s0112_beanpostprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "de.holhar.java_dev_kb.training.pcps.ch01_container.s0112_beanpostprocessor")
public class BeanPostProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeanPostProcessorApplication.class, args);
    }
}
