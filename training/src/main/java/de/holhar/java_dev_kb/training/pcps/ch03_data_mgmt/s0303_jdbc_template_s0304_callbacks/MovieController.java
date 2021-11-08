package de.holhar.java_dev_kb.training.pcps.ch03_data_mgmt.s0303_jdbc_template_s0304_callbacks;

import de.holhar.java_dev_kb.training.pcps.ch08_testing.s0803_transactional.Movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this. movieService = movieService;
    }

    @GetMapping("/movies/{id}")
    public Movie getMovie(@PathVariable("id") Long id) {
        return movieService.find(id).orElseThrow(() -> new IllegalArgumentException("Movie with id '" + id + "' not found"));
    }
}
