package de.holhar.java_dev_kb.training.pcps.ch02_aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.annotation.PostConstruct;

@SpringBootApplication(scanBasePackages = "de.holhar.java_dev_kb.training.pcps.ch02_aop")
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class AopApplication {

    @Autowired
    private ExampleService service;

    public static void main(String[] args) {
        SpringApplication.run(AopApplication.class, args);
    }

    @PostConstruct
    private void init() {
        service.doSomething();
        service.doSomethingDifferent();
    }
}
