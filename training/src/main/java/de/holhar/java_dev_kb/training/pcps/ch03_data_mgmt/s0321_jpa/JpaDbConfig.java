package de.holhar.java_dev_kb.training.pcps.ch03_data_mgmt.s0321_jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.Driver;
import java.util.Properties;

/**
 * Q3.21:
 * 1. Declare dependencies.
 * 2. Implement entity classes with mapping metadata in the form of annotations (see {@link Movie} for an example).
 * 3. Define an {@link EntityManagerFactory} bean (see {@link this#entityManagerFactory()} below).
 * 4. Define a {@link DataSource} bean (see {@link this#dataSource()} below).
 * 5. Define a {@link TransactionManager} bean (see {@link this#dataSourceTransactionManager()} below).
 * 6. Implement repositories.
 *
 * Spring Boot provides a starter module that:
 * - Provides a default set of dependencies needed for using JPA in a Spring application.
 * - Provides all the Spring beans needed to use JPA (these beans can be easily customized by declaring bean(s) with
 *   the same name(s) in the application, as is standard in Spring applications).
 * - Provides a number of default properties related to persistence and JPA (these properties can be easily
 *   customized by declaring one or more properties in the application properties-file supplying new values).
 */
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:db/test-jpa-datasource.properties")
public class JpaDbConfig {

    private static final Logger logger = LoggerFactory.getLogger(JpaDbConfig.class);

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
    public TransactionManager dataSourceTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
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
        populator.addScript(dataScript);
        return populator;
    }

    /**
     * An entity manager factory is used to interact with a persistence unit. On the entity manager factory the
     * following are typically configured:
     * - an adapter for the ORM implementation to be used by the application, for example Hibernate or EclipseLink
     * - the type of database used by the application
     * - ORM configuration properties
     * - the package(s) in the application in which to scan for entities
     */
    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPackagesToScan("de.holhar.java_dev_kb.training.pcps.ch03_data_mgmt.s0321_jpa");
        factoryBean.setDataSource(dataSource());
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setJpaProperties(hibernateProperties());
        factoryBean.afterPropertiesSet();
        return factoryBean.getNativeEntityManagerFactory();
    }

    @Value("${db.dialect}")
    private String dialect;

    @Value("${db.hbm2ddl}")
    private String hbm2ddl;

    @Bean
    public Properties hibernateProperties() {
        Properties hibernateProps = new Properties();
        hibernateProps.put("hibernate.dialect", dialect);
        hibernateProps.put("hibernate.hbm2ddl.auto", hbm2ddl);
        hibernateProps.put("hibernate.format_sql", true);
        hibernateProps.put("hibernate.use_sql_comments", true);
        hibernateProps.put("hibernate.show_sql", false);
        return hibernateProps;
    }
}
