package de.holhar.java_dev_kb.designpatterns.structural.facade;

import java.sql.Connection;

public class DbHelperFacade {

    public enum ReportType {
        HTML, PDF
    }

    public enum DbType {
        MY_SQL, ORACLE
    }

    public static void generateReport(DbType dbType, ReportType reportType, String tableName) {
        Connection conn = null;
        if (dbType == DbType.MY_SQL) {
            conn = MySqlHelper.getMySqlDbConnection();
            MySqlHelper mySqlHelper = new MySqlHelper();
            if (reportType == ReportType.HTML) {
                mySqlHelper.generateMySqlHtmlReport(tableName, conn);
            } else if (reportType == ReportType.PDF) {
                mySqlHelper.generateMySqlPfdReport(tableName, conn);
            }
        } else if (dbType == DbType.ORACLE) {
            conn = OracleHelper.getOracleDbConnection();
            OracleHelper oracleHelper = new OracleHelper();
            if (reportType == ReportType.HTML) {
                oracleHelper.generateOracleHtmlReport(tableName, conn);
            } else if (reportType == ReportType.PDF) {
                oracleHelper.generateOraclePfdReport(tableName, conn);
            }
        }
    }
}
