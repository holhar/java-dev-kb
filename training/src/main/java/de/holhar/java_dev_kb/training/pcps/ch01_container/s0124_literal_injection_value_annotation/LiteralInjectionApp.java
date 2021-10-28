package de.holhar.java_dev_kb.training.pcps.ch01_container.s0124_literal_injection_value_annotation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "de.holhar.java_dev_kb.training.pcps.ch01_container.s0124_literal_injection_value_annotation")
public class LiteralInjectionApp {

    public static void main(String[] args) {
        SpringApplication.run(LiteralInjectionApp.class, args);
    }
}
