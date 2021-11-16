package de.holhar.java_dev_kb.training.pcps.ch04_data_jpa;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Movie {

    @EmbeddedId
    private MovieId movieId;
    private String genre;
    private String director;

    public MovieId getMovieId() {
        return movieId;
    }

    public void setMovieId(MovieId movieId) {
        this.movieId = movieId;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", genre='" + genre + '\'' +
                ", director='" + director + '\'' +
                '}';
    }
}
