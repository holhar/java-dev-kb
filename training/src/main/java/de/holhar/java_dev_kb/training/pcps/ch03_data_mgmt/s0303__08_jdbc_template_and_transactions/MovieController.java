package de.holhar.java_dev_kb.training.pcps.ch03_data_mgmt.s0303__08_jdbc_template_and_transactions;

import de.holhar.java_dev_kb.training.pcps.ch08_testing.s0803_transactional.Movie;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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

    // curl -X PUT -H"Content-Type:application/json" --data '{"title":"foo","releaseDate":"2001-01-21T12:00:00.000"}' http://localhost:8080/movies
    @PutMapping("/movies")
    public void putMovie(@RequestBody Movie movie) {
        movieService.save(movie);
    }

    @GetMapping("/movies")
    public List<Map<String, Object>> getAllMovies() {
        return movieService.findAll();
    }

    @GetMapping("/movies/titles")
    public List<String> getAllTitles() {
        return movieService.findAllTitles();
    }

    @GetMapping("movies/title/{title}")
    public Map<String, Object> getByTitle(@PathVariable("title") String title) {
        return movieService.findByTitle(title);
    }

    @DeleteMapping("movies")
    public void deleteFirstEntries() {
        movieService.deleteFirstEntries();
    }
}
