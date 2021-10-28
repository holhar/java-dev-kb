package de.holhar.java_dev_kb.training.pcps.ch01_container.s0120_s0121_s0122_s0123_profiles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication(scanBasePackages = "de.holhar.java_dev_kb.training.pcps.ch01_container.s0120_s0121_s0122_s0123_profiles")
public class ProfilesApplication {

    private static final Logger logger = LoggerFactory.getLogger(ProfilesApplication.class);

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(ProfilesApplication.class, args);
        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(bean -> logger.info("{}", bean));
    }
}
