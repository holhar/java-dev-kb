package de.holhar.java_dev_kb.training.pcps.ch04_data_jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication(scanBasePackages = "de.holhar.java_dev_kb.training.pcps.ch04_data_jpa")
public class DataJpaApplication {

    private static final Logger logger = LoggerFactory.getLogger(DataJpaApplication.class);

    @Autowired
    private MovieRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(DataJpaApplication.class, args);
    }

    @PostConstruct
    private void init() {
        logger.info("----");
        logger.info("findAll:");
        repository.findAll()
                .forEach(movie -> logger.info("-- {}", movie));

        logger.info("----");
        logger.info("findFirst5ByGenreContainingOrderByDirectorDesc:");
        repository.findFirst5ByGenreContainingOrderByDirectorDesc("on")
                .forEach(m -> logger.info("-- '{}' - '{}'", m.getDirector(), m.getMovieId().getTitle()));

        logger.info("----");
        logger.info("findByGenre:");
        repository.findByGenre("Thriller")
                .forEach(m -> logger.info("-- '{}' ({})", m.getMovieId().getTitle(), m.getMovieId().getReleaseDate()));
    }
}
