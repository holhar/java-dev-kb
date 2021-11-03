package de.holhar.java_dev_kb.training.pcps.ch08_testing.s0803_transactional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(scanBasePackages = "de.holhar.java_dev_kb.training.pcps.ch08_testing.s0803_transactional")
@PropertySource("classpath:datasource.properties")
public class TransactionApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionApplication.class, args);
    }
}
