package de.holhar.java_dev_kb.training.pcps.ch08_testing.s0803_transactional;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Service
public class MovieService {

    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void save(Movie movie) {
        movieRepository.save(movie);
    }

    public long size() {
        return movieRepository.count();
    }

    @PostConstruct
    private void init() {
        Movie movie = new Movie();
        movie.setTitle("Brazil");
        movie.setReleaseDate(LocalDate.of(1985, 4, 26));
        save(movie);
    }
}
