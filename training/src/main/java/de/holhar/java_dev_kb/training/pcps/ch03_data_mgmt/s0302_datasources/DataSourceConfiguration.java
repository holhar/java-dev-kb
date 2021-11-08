package de.holhar.java_dev_kb.training.pcps.ch03_data_mgmt.s0302_datasources;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:test-datasource.properties")
public class DataSourceConfiguration {

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

    /**
     * {@link DataSource} is the interface from which all data-source classes related to SQL stem.
     *
     * Below is an example for configuring a DataSource for a standalone application via Java configuration. In a
     * Spring Boot application this can be achieved by setting a couple of properties, see
     * training/src/main/resources/datasource.properties for an example
     */
    @Bean
    public DataSource dataSourceStandaloneApplication() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    /**
     * For applications deployed to a server, the datasource is obtained by performing a JNDI lookup. For Spring Boot
     * applications there is only one property that must be set:
     *
     * spring.datasource.jndi-name=java:comp/env/jdbc/MyDatabase
     */
    //@Bean
    public DataSource dataSourceServerDeployedApplication() {
        JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
        DataSource dataSource = dataSourceLookup.getDataSource("java:comp/env/jdbc/MyDatabase");
        return dataSource;
    }
}
