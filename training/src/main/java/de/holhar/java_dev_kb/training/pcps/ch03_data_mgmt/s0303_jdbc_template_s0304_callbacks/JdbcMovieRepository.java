package de.holhar.java_dev_kb.training.pcps.ch03_data_mgmt.s0303_jdbc_template_s0304_callbacks;

import de.holhar.java_dev_kb.training.pcps.ch08_testing.s0803_transactional.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Repository
public class JdbcMovieRepository {

    private static final Logger logger = LoggerFactory.getLogger(JdbcMovieRepository.class);

    /**
     * Q3.3:
     * JdbcTemplate is the central class in the JDBC core package. It simplifies the use of JDBC and helps to avoid
     * common errors. It executes core JDBC workflow, leaving application code to provide SQL and extract results.
     * This class executes SQL queries or updates, initiating iteration over ResultSets and catching JDBC exceptions
     * and translating them to the generic, more informative exception hierarchy defined in the org.springframework.dao
     * package.
     *
     * JdbcTemplate applies the template design pattern.
     * In object-oriented programming, the template method is one of the behavioral design patterns identified by
     * Gamma et al. in the book Design Patterns. The template method is a method in a superclass, usually an abstract
     * superclass, and defines the skeleton of an operation in terms of a number of high-level steps. These steps are
     * themselves implemented by additional helper methods in the same class as the template method.
     *
     * The helper methods may be either abstract methods, in which case subclasses are required to provide concrete
     * implementations, or hook methods, which have empty bodies in the superclass. Subclasses can (but are not
     * required to) customize the operation by overriding the hook methods. The intent of the template method is to
     * define the overall structure of the operation, while allowing subclasses to refine, or redefine, certain steps.
     *
     * Like described above, it is possible to write concrete classes inheriting from JdbcTemplate and provide custom
     * implementations of the algorithm steps defined.
     *
     * Benefits from using JdbcTemplate are simplification, exception handling and translation, functionality
     * customization, proper db resources handling etc...
     */
    private final JdbcTemplate jdbcTemplate;

    /**
     * Q3.4:
     * A callback is code or a reference to a piece of code that is passed as an argument to a method that, at some
     * point during the execution of the methods, will call the code passed as an arguments. In Java, a callback
     * can be a reference to a Java object that implements a certain interface or, starting with Java 8, a lambda
     * expression.
     *
     * The three JdbcTemplate callback interfaces are {@link RowMapper}, {@link RowCallbackHandler}, and
     * {@link ResultSetExtractor}.
     */

    /**
     * RowMapper is an interface used by JdbcTemplate for mapping rows of a ResultSet on a per-row basis.
     * Implementations of this interface perform the actual work of mapping each row to a result object, but don't
     * need to worry about exception handling. SQLExceptions will be caught and handled by the calling JdbcTemplate.
     * Typically used either for JdbcTemplate's query methods or for out parameters of stored procedures. RowMapper
     * objects are typically stateless and thus reusable; they are an ideal choice for implementing row-mapping logic
     * in a single place.
     *
     * Use RowMapper<T> when each row of the ResultSet maps to a domain object.
     */
    private final RowMapper<Movie> movieRowMapper = (rs, rowNum) -> {
        var id = rs.getLong("id");
        var title = rs.getString("title");
        var releaseDate = rs.getTimestamp("releaseDate");
        Movie movie = new Movie();
        movie.setId(id);
        movie.setTitle(title);
        movie.setReleaseDate(releaseDate.toLocalDateTime().toLocalDate());
        return movie;
    };

    /**
     * ResultSetExtractor is a callback interface used by JdbcTemplate's query methods. Implementations of this
     * interface perform the actual work of extracting results from a ResultSet, but don't need to worry about
     * exception handling. SQLExceptions will be caught and handled by the calling JdbcTemplate.
     * This interface is mainly used within the JDBC framework itself. A RowMapper is usually a simpler choice for
     * ResultSet processing, mapping one result object per row instead of one result object for the entire ResultSet.
     *
     * Use ResultSetExtractor<T> when multiple rows, or multiple records from different tables returned in a
     * ResultSet map to a single object.
     */
    private final ResultSetExtractor<String> titleResultSetExtractor = (rs) -> {
        var title = "";
        while (rs.next()) {
            title = rs.getString("title");
        }
        return title;
    };

    /**
     * An interface used by JdbcTemplate for processing rows of a ResultSet on a per-row basis. Implementations of
     * this interface perform the actual work of processing each row but don't need to worry about exception handling.
     * SQLExceptions will be caught and handled by the calling JdbcTemplate.
     * In contrast to a ResultSetExtractor, a RowCallbackHandler object is typically stateful: It keeps the result
     * state within the object, to be available for later inspection. See RowCountCallbackHandler for a usage example.
     *
     * Use RowCallbackHandler when no value should be returned.
     */
    public static class MovieLoggerRowCallbackHandler implements RowCallbackHandler {

        private final Logger logger;

        public MovieLoggerRowCallbackHandler(Logger logger) {
            this.logger = logger;
        }

        @Override
        public void processRow(ResultSet rs) throws SQLException {
            logger.info("Obtained movie: '{}', released in '{}'", rs.getString("title"), rs.getTimestamp("releaseDate").toLocalDateTime().toLocalDate());
        }
    }

    public JdbcMovieRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Movie> findById(Long id) {
        var sql = "select id, title, releaseDate from Movie where id=?";
        // Applying all JdbcTemplate callback interfaces for demo purposes:
        logger.info("Movie title by titleResultSetExtractor: '{}'", jdbcTemplate.query(sql, titleResultSetExtractor, id));
        jdbcTemplate.query(sql, new MovieLoggerRowCallbackHandler(logger), id);
        return Optional.of(jdbcTemplate.queryForObject(sql, movieRowMapper, id));
    }

    public void save(Movie movie) {
        var sql = "insert into Movie (title, releaseDate) values (?, ?)";
        LocalDateTime localDateTime = LocalDateTime.of(movie.getReleaseDate(), LocalTime.now());
        jdbcTemplate.query(sql, movieRowMapper, movie.getTitle(), Timestamp.valueOf(localDateTime));
    }


}
