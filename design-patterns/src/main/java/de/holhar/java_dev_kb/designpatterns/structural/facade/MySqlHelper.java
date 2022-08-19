package de.holhar.java_dev_kb.designpatterns.structural.facade;

import java.sql.Connection;

public class MySqlHelper {

    public static Connection getMySqlDbConnection() {
        // Get MySql Db connection using connection parameters
        return null;
    }

    public void generateMySqlPfdReport(String tableName, Connection conn) {
        // Get data from table and generate pdf report
    }

    public void generateMySqlHtmlReport(String tableName, Connection conn) {
        // Get data from table and generate html report
    }
}
