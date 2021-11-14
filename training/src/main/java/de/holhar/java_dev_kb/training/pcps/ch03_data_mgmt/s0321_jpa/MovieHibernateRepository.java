package de.holhar.java_dev_kb.training.pcps.ch03_data_mgmt.s0321_jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
@Transactional
public class MovieHibernateRepository {

    private final EntityManager entityManager;

    public MovieHibernateRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public List<Movie> findAll() {
        return entityManager.createQuery("SELECT m FROM Movie m").getResultList();
    }
}
