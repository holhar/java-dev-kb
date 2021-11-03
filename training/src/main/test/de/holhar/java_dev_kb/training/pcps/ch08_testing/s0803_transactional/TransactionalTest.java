package de.holhar.java_dev_kb.training.pcps.ch08_testing.s0803_transactional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfiguration.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TransactionalTest {

    @Autowired
    private MovieService movieService;

    /**
     * Describes a transaction attribute on an individual method or on a class.
     *
     * At the class level, this annotation applies as a default to all methods of the declaring class and its
     * subclasses.
     *
     * Restores database state after the test was run. A transaction in which a test method is executed will be
     * rolled back after the test has been run.
     */
    @Rollback
    @Transactional
    @Test
    @Order(1)
    void saveMovie() {
        Movie movie = new Movie();
        movie.setTitle("Fight Club");
        movie.setReleaseDate(LocalDate.of(1999, 11, 11));
        movieService.save(movie);
        assertEquals(2, movieService.size());
    }

    @Test
    @Order(2)
    void count() {
        assertEquals(1, movieService.size());
    }
}
