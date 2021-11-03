package de.holhar.java_dev_kb.training.pcps.ch08_testing.s0803_transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {
}
