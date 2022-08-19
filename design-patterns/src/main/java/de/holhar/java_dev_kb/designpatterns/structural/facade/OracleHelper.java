package de.holhar.java_dev_kb.designpatterns.structural.facade;

import java.sql.Connection;

public class OracleHelper {

    public static Connection getOracleDbConnection() {
        // Get Oracle Db connection using connection parameters
        return null;
    }

    public void generateOraclePfdReport(String tableName, Connection conn) {
        // Get data from table and generate pdf report
    }

    public void generateOracleHtmlReport(String tableName, Connection conn) {
        // Get data from table and generate html report
    }
}
