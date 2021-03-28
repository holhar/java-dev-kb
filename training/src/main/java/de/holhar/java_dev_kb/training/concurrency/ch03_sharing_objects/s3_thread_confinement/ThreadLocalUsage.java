package de.holhar.java_dev_kb.training.concurrency.ch03_sharing_objects.s3_thread_confinement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Using ThreadLocal to ensure thread confinement.
 */
public class ThreadLocalUsage {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/db_name";

    /*
     * ThreadLocal allows you to associate a per-thread value with a value-holding object -> maintain a separate copy
     * of the value for each thread that uses it.
     */
    private static final ThreadLocal<Connection> connectionHolder = new ThreadLocal<>() {
        @Override
        public Connection initialValue() {
            try {
                return DriverManager.getConnection(DB_URL);
            } catch (SQLException throwables) {
                throw new IllegalStateException("Something terrible happened!");
            }
        }
    };

    public static Connection getConnection() {
        return connectionHolder.get();
    }
}
