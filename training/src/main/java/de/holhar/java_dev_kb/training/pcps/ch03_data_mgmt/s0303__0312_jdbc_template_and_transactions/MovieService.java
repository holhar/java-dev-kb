package de.holhar.java_dev_kb.training.pcps.ch03_data_mgmt.s0303__0312_jdbc_template_and_transactions;

import de.holhar.java_dev_kb.training.pcps.ch08_testing.s0803_transactional.Movie;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MovieService {

    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void save(Movie movie) {
        movieRepository.save(movie);
    }

    public Optional<Movie> find(long id) {
        return movieRepository.findById(id);
    }

    public List<Map<String, Object>> findAll() {
        return movieRepository.findAll();
    }

    public List<String> findAllTitles() {
        return movieRepository.findAllTitles();
    }

    public Map<String, Object> findByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    /**
     * Q3.8:
     * A transaction is an operation that consists of a number of tasks that takes place as a single unit –
     * either all tasks are performed or no tasks are performed. If a task that is part of a transaction do not
     * complete successfully, the other tasks in the transaction will either not be performed or, for tasks
     * that have already been performed, be reverted.
     *
     * Local transactions are transactions associated with one single resource, such as one single database
     * or a queue of a message broker, but not both in one and the same transaction.
     *
     * Global transactions allow for transactions to span multiple transactional resources. As an example
     * consider a global transaction that spans a database update operation and the posting of a message to
     * the queue of a message broker.
     *
     * NOTE: {@link EnableTransactionManagement} must be set and a {@link TransactionManager} must be configured
     * (see {@link DataMgmtConfig}).
     *
     * Q3.10.a:
     * Transaction is used for declarative transaction management and ban be applied to methods and classes. It
     * specifies the transaction attributes for the method(s) it is active for:
     * - isolation: the transaction isolation level (see Q3.11.a and propagation for an elaboration)
     * - noRollbackFor: exception classes that do not trigger a transaction rollback
     * - noRollbackForClassName: names of exception classes that do not trigger a transaction rollback
     * - propagation: transaction propagation (see Q3.13 for an elaboration)
     * - readOnly: set to 'true', if a read-only transaction, allowing for corresponding optimizations at runtime
     * - rollbackFor: exception classes that do trigger a transaction rollback
     * - rollbackForClassName: names of exception classes that do trigger a transaction rollback
     * - timeout: transaction timeout (see propagation)
     * - transactionManager: name of the transaction manager Spring bean
     */
    @Transactional
    public void deleteFirstEntries() {
        movieRepository.deleteFirstEntries();
    }
}
