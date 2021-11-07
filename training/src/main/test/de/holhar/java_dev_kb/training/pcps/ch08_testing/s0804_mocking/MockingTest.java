package de.holhar.java_dev_kb.training.pcps.ch08_testing.s0804_mocking;

import de.holhar.java_dev_kb.training.pcps.ch08_testing.s0803_transactional.Movie;
import de.holhar.java_dev_kb.training.pcps.ch08_testing.s0803_transactional.MovieRepository;
import de.holhar.java_dev_kb.training.pcps.ch08_testing.s0803_transactional.MovieService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

/**
 * Mockito and EasyMock allow dynamic mock object creation, which simulate the behaviour of dependencies of the class
 * under test. The behaviour of the mock, i.e. the results the used methods yield are programmable in dedicated tests.
 * Additionally, it is possible to verify method invocation sequences, parameters supplied to the methods etc.
 */
@ExtendWith(MockitoExtension.class)
class MockingTest {

    @InjectMocks
    private MovieService service;

    @Mock
    private MovieRepository repository;

    @Test
    void save() {
        final Movie movie = new Movie();
        movie.setTitle("Dune");
        movie.setReleaseDate(LocalDate.of(2021, Month.SEPTEMBER, 16));

        service.save(movie);

        ArgumentCaptor<Movie> movieCaptor = ArgumentCaptor.forClass(Movie.class);
        verify(repository).save(movieCaptor.capture());
        assertEquals("Dune", movieCaptor.getValue().getTitle());
        assertEquals(LocalDate.of(2021, Month.SEPTEMBER, 16), movieCaptor.getValue().getReleaseDate());
    }
}
