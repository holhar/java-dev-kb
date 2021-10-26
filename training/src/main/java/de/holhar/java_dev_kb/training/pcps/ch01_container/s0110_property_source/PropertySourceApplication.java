package de.holhar.java_dev_kb.training.pcps.ch01_container.s0110_property_source;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "de.holhar.java_dev_kb.training.pcps.ch01_container.s0110_property_source")
public class PropertySourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PropertySourceApplication.class, args);
    }
}
