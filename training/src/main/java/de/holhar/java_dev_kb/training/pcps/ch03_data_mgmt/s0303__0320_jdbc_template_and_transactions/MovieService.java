package de.holhar.java_dev_kb.training.pcps.ch03_data_mgmt.s0303__0320_jdbc_template_and_transactions;

import de.holhar.java_dev_kb.training.pcps.ch08_testing.s0803_transactional.Movie;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
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
     * - isolation: the transaction isolation level (see Q3.11.a and Q3.13 for an elaboration)
     * - noRollbackFor: exception classes that do not trigger a transaction rollback
     * - noRollbackForClassName: names of exception classes that do not trigger a transaction rollback
     * - propagation: transaction propagation (see Q3.13 for an elaboration)
     * - readOnly: set to 'true', if a read-only transaction, allowing for corresponding optimizations at runtime
     * - rollbackFor: exception classes that do trigger a transaction rollback
     * - rollbackForClassName: names of exception classes that do trigger a transaction rollback
     * - timeout: transaction timeout (see propagation)
     * - transactionManager: name of the transaction manager Spring bean
     *
     * Q3.14
     * A self-invocation of a proxied Spring bean effectively bypasses the proxy and thus also
     * any transaction interceptor managing transactions. Thus, the second method, the method being
     * invoked from another method in the bean, will execute in the same transaction context as the first.
     * Any configuration in a @Transactional annotation on the second method will not come into effect.
     *
     * Q3.15
     * The @Transactional annotation can be used on class and method level in classes and interfaces. When using
     * Spring AOP proxies, only @Transactional annotations on public methods will have any effect – applying the
     * @Transactional annotation to protected or private methods or methods with package visibility will not cause
     * errors but will not give the desired transaction management, as above.
     *
     * Q3.17
     * The default rollback policy of Spring transaction management is that an automatic rollback only takes place in
     * the case of an unchecked exception being thrown. The types of exceptions that are to cause a rollback can be
     * configured using the rollbackFor element of the @Transactional annotation. In addition, the types of
     * exceptions that not are to cause rollbacks can also be configured using the noRollbackFor element.
     */
    @Transactional(
            /**
             * Q3.13
             * Transaction propagation determines the way an existing transaction is used, depending on the
             * transaction propagation configured in the @Transactional annotation on the method, when the method is
             * invoked (see docs for elaboration of all seven types supported)
            */
            propagation = Propagation.REQUIRED // Support a current transaction, create a new one if none exists
    )
    public void deleteFirstEntries() {
        movieRepository.deleteFirstEntries();
    }
}
