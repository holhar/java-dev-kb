package de.holhar.java_dev_kb.training.pcps.ch03_data_mgmt.s0303_jdbc_template_s0304_callbacks;

import de.holhar.java_dev_kb.training.pcps.ch08_testing.s0803_transactional.Movie;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieService {

    private JdbcMovieRepository jdbcMovieRepository;

    public MovieService(JdbcMovieRepository jdbcMovieRepository) {
        this.jdbcMovieRepository = jdbcMovieRepository;
    }

    public void save(Movie movie) {
        jdbcMovieRepository.save(movie);
    }

    public Optional<Movie> find(long id) {
        return jdbcMovieRepository.findById(id);
    }
}
