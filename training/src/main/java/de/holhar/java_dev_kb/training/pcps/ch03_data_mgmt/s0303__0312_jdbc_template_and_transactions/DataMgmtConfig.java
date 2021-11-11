package de.holhar.java_dev_kb.training.pcps.ch03_data_mgmt.s0303__0312_jdbc_template_and_transactions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.Driver;

/**
 * Q3.12:
 * The @EnableTransactionManagement annotation is to annotate exactly one configuration class in an application in
 * order to enable annotation-driven transaction management using the @Transactional annotation.
 *
 * Components registered when the @EnableTransactionManagement annotation is used are:
 * - A {@link org.springframework.transaction.interceptor.TransactionInterceptor}: intercepts calls to @Transactional
 *   methods creating new transactions as necessary etc.
 * - A JDK Proxy or AspectJ advice: this advice intercepts methods annotated with @Transactional (or methods that are
 *   located in a class annotated with @Transactional).
 *
 * The @EnableTransactionmanagement annotation has the following three optional attributes:
 * - mode: allows for selecting the type of advice that should be used with transactions. Possible values are
 *   AdviceMode.ASPECTJ and AdviceMode.PROXY with the latter being the default.
 * - order: precedence of the transaction advice when more than one advice is applied to a join-point. Default value
 *   is Ordered.LOWEST_PRECEDENCE.
 * - proxyTargetClass: 'true' if CGLIB proxies are to be used, 'false' if JDK interface-based proxies are to be used in
 *   the application (affects proxies for all Spring managed beans in the application!). Applicable  only if the mode
 *   element is AdviceMode.PROXY.
 */
@EnableTransactionManagement
@Configuration
@PropertySource("classpath:db/test-datasource.properties")
public class DataMgmtConfig {

    private static final Logger logger = LoggerFactory.getLogger(DataMgmtConfig.class);

    @Value("${db.driverClassName}")
    private String driverClassName;
    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public DataSource dataSource() {
        try {
            var dataSource = new SimpleDriverDataSource();
            Class<? extends Driver> driver = (Class<? extends Driver>) Class.forName(driverClassName);
            dataSource.setDriverClass(driver);
            dataSource.setUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            DatabasePopulatorUtils.execute(databasePopulator(), dataSource);
            return dataSource;
        } catch (Exception e) {
            logger.error("Failed to init DataSource: '{}'", e.getMessage(), e);
            return null;
        }
    }

    @Value("classpath:db/schema.sql")
    private Resource schemaScript;

    @Value("classpath:db/test-data.sql")
    private Resource dataScript;

    private DatabasePopulator databasePopulator() {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(schemaScript);
        populator.addScript(dataScript);
        return populator;
    }

    /**
     * Q3.9: Transaction management is a cross-cutting concern, in Spring framework implemented with Spring AOP.
     *
     * Q3.10:
     * To make use of Spring transactions a {@link PlatformTransactionManager} must be declared, i.e. a bean class
     * implementing this interface must be defined, that provides transaction management for transactional resource(s)
     * that are to be used. Furthermore, {@link EnableTransactionManagement} must be applied to exactly one
     * @Configuration class of the dedicated application context. Afterwards, transaction boundaries can be declared
     * in the application code by annotating classes or single methods by annotating corresponding spots with
     * {@link org.springframework.transaction.annotation.Transactional} - see {@link MovieService#deleteFirstEntries()}
     * for an example.
     *
     * Q3.10.a:
     * PlatformTransactionManager is the base interface for all transaction managers that can be used in the Spring
     * framework’s transaction infrastructure. Transaction managers (implementing this interface) can be used
     * directly by applications, but it is recommended to use declarative transactions or the TransactionTemplate class.
     *
     * The PlatformTransactionManager interface contain the following methods:
     * - void commit(TransactionStatus)
     * - void rollback(TransactionStatus)
     * - TransactionStatus getTransaction(TransactionDefinition)
     */
    @Bean
    public PlatformTransactionManager dataSourceTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public JdbcTemplate customJdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean(destroyMethod = "destroy")
    public CleanUp cleanUp() {
        return new CleanUp(customJdbcTemplate());
    }
}

class CleanUp {

    private JdbcTemplate jdbcTemplate;

    CleanUp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private void destroy() {
        jdbcTemplate.execute("DROP ALL OBJECTS DELETE FILES;");
    }
}
