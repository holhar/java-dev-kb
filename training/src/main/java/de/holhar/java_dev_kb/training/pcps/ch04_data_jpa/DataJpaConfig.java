package de.holhar.java_dev_kb.training.pcps.ch04_data_jpa;

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

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.Driver;
import java.util.Properties;

@Configuration
// Q4.2:
//@EnableJpaRepositories <== needed in case of not manually configured entityManagerFactory
@PropertySource("classpath:db/test-jpa-datasource.properties")
public class DataJpaConfig {

    private static final Logger logger = LoggerFactory.getLogger(DataJpaConfig.class);

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
    public TransactionManager transactionManager() {
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

    @Value("classpath:db/test-jpa-data.sql")
    private Resource dataScript;

    private DatabasePopulator databasePopulator() {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(dataScript);
        return populator;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPackagesToScan("de.holhar.java_dev_kb.training.pcps.ch04_data_jpa");
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
