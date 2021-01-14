package de.holhar.java_dev_kb.training.ocp8.ch10_jdbc.sec03_statement_execution;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.getZooDbBaseUrl;
import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class UsingStatements {

    public static void main(String[] args) throws SQLException {
        ResultSet rs = null;
        try (Connection conn = DriverManager.getConnection(getZooDbBaseUrl());
             Statement stmt = conn.createStatement()) {
            // Using executeUpdate() for INSERT, UPDATE, and DELETE, which returns int value, that represents
            //  the affected row number.
            int result = stmt.executeUpdate("INSERT INTO species VALUES(10, 'Deer', 3)");
            println(result); // 1
            result = stmt.executeUpdate("UPDATE species SET name = '' WHERE name = 'None'");
            println(result); // 0
            result = stmt.executeUpdate("DELETE FROM species WHERE id = 10");
            println(result); // 1

            // Using executeQuery for SELECT statements, which returns a ResultSet
            rs = stmt.executeQuery("SELECT * FROM species");

            // Using execute() statement, which CAN be a ResultSet
            boolean isResultSet = stmt.execute("SELECT * FROM animal");
            if (isResultSet) {
                rs = stmt.getResultSet();
                println("Ran a query");
            } else {
                result = stmt.getUpdateCount();
                println("Ran an update");
            }
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                // ignore...
            }
        }
    }
}
