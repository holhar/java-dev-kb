package de.holhar.java_dev_kb.training.pcps.ch04_data_jpa;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class MovieId implements Serializable {

    private String title;
    private LocalDate releaseDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieId movieId = (MovieId) o;
        return title.equals(movieId.title) && releaseDate.equals(movieId.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, releaseDate);
    }

    @Override
    public String toString() {
        return "MovieId{" +
                "title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
