package de.holhar.java_dev_kb.training.ocp8.ch10_jdbc.sec04_resultset_data_retrieval;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.getZooDbBaseUrl;
import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class ResultSetDataRetrieval {

    public static void main(String[] args) {

        // Closing DB connection without try-with-resources statements
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(getZooDbBaseUrl());
            stmt = conn.createStatement();
            /*
             * Reading a ResultSet
             */
            Map<Integer, String> idToNameMap = new HashMap<>();
            rs = stmt.executeQuery("SELECT id, name FROM species");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                // Column values can be retrieved via column index as well (which are 1-indexed), e.g. BAD CODE: rs.getInt(0)
                //int id = rs.getInt(1);
                //String name = rs.getString(2);
                idToNameMap.put(id, name);
            }
            println(idToNameMap);
            println("");

            /*
             * Getting data for a column
             */
            rs = stmt.executeQuery("SELECT date_born FROM animal WHERE name = 'Elsa'");
            // CHECK IF RESULT_SET IS PRESENT TO AVOID EXCEPTIONS!
            if (rs.next()) {
                java.sql.Date sqlDate = rs.getDate(1);
                LocalDate localDate = sqlDate.toLocalDate();
                println(localDate);

                java.sql.Time sqlTime = rs.getTime(1);
                LocalTime localTime = sqlTime.toLocalTime();
                println(localTime);

                java.sql.Timestamp sqlTimestamp = rs.getTimestamp(1);
                LocalDateTime localDateTime = sqlTimestamp.toLocalDateTime();
                println(localDateTime);
            }
            println("");

            rs = stmt.executeQuery("SELECT id, name FROM species");
            if (rs.next()) {
                Object idField = rs.getObject("id");
                Object nameField = rs.getObject("name");
                if (idField instanceof Integer) {
                    int id = (Integer) idField;
                    println(id);
                }
                if (nameField instanceof String) {
                    String name = (String) nameField;
                    println(name);
                }
            }
            println("");

            /*
             * Scrolling ResultSet
             */
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("SELECT id FROM species ORDER BY id");
            rs.afterLast();
            println(rs.previous());   // true
            println(rs.getInt(1)); // 2
            println(rs.previous());   // true
            println(rs.getInt(1)); // 1
            println(rs.last());      // true
            println(rs.getInt(1)); // 2
            println(rs.first());     // true
            println(rs.getInt(1));
            rs.beforeFirst();
            //println(rs.getInt(1)); // throws SQLException
            println("");

            rs = stmt.executeQuery("SELECT id FROM animal ORDER BY id");
            println(rs.absolute(2));        // true
            println(rs.getString("id"));    // 2
            println(rs.absolute(0));        // false
            println(rs.absolute(5));        // true
            println(rs.getString("id"));    // 5
            // Start moving from last position:
            println(rs.absolute(-2));       // true
            println(rs.getString("id"));    // 4
            println("");

            rs = stmt.executeQuery("SELECT id FROM animal ORDER BY id");
            println(rs.next());                 // true
            println(rs.getString("id"));    // 1
            println(rs.relative(2));        // true
            println(rs.getString("id"));    // 3
            println(rs.relative(-1));        // true
            println(rs.getString("id"));    // 2
            println(rs.relative(4));        // false
        } catch (SQLException e) {
            // Dealing with SQExceptions
            println(e.getMessage());
            println(e.getSQLState());
            println(e.getErrorCode());
        } finally {
            // Closing DB connection without try-with-resources statements
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                // ignore...
            }

            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception e) {
                // ignore...
            }

            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                // ignore...
            }
        }
    }
}
