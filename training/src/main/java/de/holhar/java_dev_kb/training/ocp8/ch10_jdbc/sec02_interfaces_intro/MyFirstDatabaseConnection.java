package de.holhar.java_dev_kb.training.ocp8.ch10_jdbc.sec02_interfaces_intro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.getZooDbBaseUrl;
import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class MyFirstDatabaseConnection {

    public static void main(String[] args) throws SQLException {
        try (Connection conn = DriverManager.getConnection(getZooDbBaseUrl());
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT name FROM animal")) {
            while (rs.next()) {
                println(rs.getString(1));
            }
        }
    }
}
