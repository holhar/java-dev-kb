package de.holhar.java_dev_kb.training.pcps.ch08_testing.s0806_s0807_spring_boot_testing;

import de.holhar.java_dev_kb.training.pcps.ch08_testing.s0803_transactional.Movie;
import de.holhar.java_dev_kb.training.pcps.ch08_testing.s0803_transactional.MovieRepository;
import de.holhar.java_dev_kb.training.pcps.ch08_testing.s0803_transactional.MovieService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.core.env.PropertySourcesPropertyResolver;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Q8.6:
 * Spring Boot simplifies writing tests by adding the following features:
 * - Provides starter module 'spring-boot-starter-test', which adds the following test-scoped dependencies:
 *   JUnit, Spring Test, Spring Boot Test, AssertJ, Hamcrest, Mockito, JSONAssert, and JSONPath.
 * - Provides @MockBean and @SpyBean annotations that allow the creation of Mockito mocks and spy beans that are
 *   added to the Spring application context.
 * - Provides @SpringBootTest annotation to enable running Spring Boot bases tests (more on that below)
 * - Provides @WebMvcTest and @WebFluxTest annotation that enable creating tests that only test MVC or WebFlux
 *   components, without loading the whole application context
 * - Provides a mock web environment, or an embedded server if so desired, when testing Spring Boot web applications
 * - Has a starter module named 'spring-boot-test-autoconfigure' that includes a number of annotations that enable
 *   selecting specific auto-configuration classes to load and not to load when creating the application context for a
 *   test (thus, avoids loading all auto-configuration classes for a test)
 * - Auto-configuration for tests related to several technologies that can be used in Spring Boot applications. E.g.
 *   JPA, JDBC, MongoDB, Neo4J, Redis, ...
 *
 * Q8.7:
 * @SpringBootTest annotation the following features:
 * - Uses SpringBootContextLoader as default ContextLoader, provided that no other ContextLoader is specified using
 *   the @ContextConfiguration annotation
 * - Searches for a @SpringBootConfiguration, if no nested @Configuration class is present in the test class, and no
 *   explicit @Configuration classes are specified in the @SpringBootTest annotation.
 * - Allows custom Environment properties to be defined using the properties attribute
 * - Registers a TestRestTemplate and WebTestClient bean for use in web tests that are using a fully running web server
 * - Provides support for different web environment modes to create for the test, using the webEnvironment attribute
 */
@SpringBootTest(
        classes = TestConfiguration.class,
        properties = { "app.foo=bar" },
        webEnvironment = SpringBootTest.WebEnvironment.NONE
)
class SpringBootTestTest {
    
    @Autowired
    private MovieService movieService;
    
    @MockBean
    private MovieRepository movieRepository;
    
    @Test
    void save() {
        final Movie movie = new Movie();
        movie.setTitle("The French Dispatch");
        movie.setReleaseDate(LocalDate.of(2021, Month.OCTOBER, 21));
        
        movieService.save(movie);

        ArgumentCaptor<Movie> movieCaptor = ArgumentCaptor.forClass(Movie.class);
        verify(movieRepository, times(2)).save(movieCaptor.capture());
        assertEquals("The French Dispatch", movieCaptor.getValue().getTitle());
        assertEquals(LocalDate.of(2021, Month.OCTOBER, 21), movieCaptor.getValue().getReleaseDate());

    }
}
