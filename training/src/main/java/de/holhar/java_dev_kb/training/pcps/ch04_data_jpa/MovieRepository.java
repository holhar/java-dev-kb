package de.holhar.java_dev_kb.training.pcps.ch04_data_jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 * Q4.1:
 * A repository interface or Spring Data repository, is a repository that needs no implementation and that supports
 * the basic CRUD operations. Such a repository is declared as an interface that typically extend a Repository
 * interface or an interface extending the Repository interface (e.g. {@link JpaRepository}). The Repository uses Java
 * generics and takes two type arguments: an entity type and a type of the primary key of entities.
 *
 * Q4.2:
 * - the belonging entity class must be properly annotated with @Entity ({@link Movie})
 * - the example below applies a custom primary key class ({@link MovieId}) that must be annotated with @Embeddable
 * - repositories are defined as interfaces in order for Spring Data to be able to use the JDK dynamic
 *   proxy mechanism to create the proxy objects that intercept calls to repositories
 *
 * Q4.4:
 * For a Spring Data repository a JDK dynamic proxy is created which intercepts all calls to the repository. The
 * default behavior is to route calls to the default repository implementation, which in Spring Data JPA is the
 * SimpleJpaRepository class. It is possible to customize either the implementation of one specific repository type
 * or customize the implementation used for all repositories.
 */
public interface MovieRepository extends JpaRepository<Movie, MovieId> {

    /**
     * Q4.3:
     * Defining a custom find method. Spring Data will recognize these find methods and supplies an implementation
     * for these methods. The naming convention of these finder methods are:
     *
     * find(First[count])By[property expression][comparison operator][ordering operator]
     */
    List<Movie> findFirst5ByGenreContainingOrderByDirectorDesc(String genre);

    /**
     * Q4.5:
     * The @Query annotation allows for specifying a query to be used with a Spring Data JPA repository method. This
     * allows for customizing the query used for the annotated repository method or supplying a query that is to be
     * used for a repository method that do not adhere to the finder method naming convention described earlier.
     */
    @Query("select m from Movie m where m.genre = :genre")
    List<Movie> findByGenre(String genre);
}
