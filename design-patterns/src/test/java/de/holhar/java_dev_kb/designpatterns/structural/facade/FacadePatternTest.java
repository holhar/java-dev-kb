package de.holhar.java_dev_kb.designpatterns.structural.facade;

import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class FacadePatternTest {

    @Test
    void facadePatternTest() {
        String tableName = "Employee";

        // Generating MySql HTML report and Oracle PDF report without using Facade
        Connection mySqlDbConnection = MySqlHelper.getMySqlDbConnection();
        MySqlHelper mySqlHelper = new MySqlHelper();
        mySqlHelper.generateMySqlHtmlReport(tableName, mySqlDbConnection);
        Connection oracleDbConnection = OracleHelper.getOracleDbConnection();
        OracleHelper oracleHelper = new OracleHelper();
        oracleHelper.generateOraclePfdReport(tableName, oracleDbConnection);

        // Generating MySql HTML report and Oracle PDF report using Facade
        DbHelperFacade.generateReport(DbHelperFacade.DbType.MY_SQL, DbHelperFacade.ReportType.HTML, tableName);
        DbHelperFacade.generateReport(DbHelperFacade.DbType.ORACLE, DbHelperFacade.ReportType.PDF, tableName);
    }
}
