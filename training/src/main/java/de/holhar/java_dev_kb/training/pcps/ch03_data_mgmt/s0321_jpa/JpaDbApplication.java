package de.holhar.java_dev_kb.training.pcps.ch03_data_mgmt.s0321_jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication(scanBasePackages = "de.holhar.java_dev_kb.training.pcps.ch03_data_mgmt.s0321_jpa")
public class JpaDbApplication {

    private static final Logger logger = LoggerFactory.getLogger(JpaDbApplication.class);

    @Autowired
    private MovieHibernateRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(JpaDbApplication.class, args);
    }

    @PostConstruct
    private void init() {
        List<Movie> movies = repository.findAll();
        movies.forEach(movie -> logger.info("Movie title '{}'", movie.getTitle()));
    }
}
