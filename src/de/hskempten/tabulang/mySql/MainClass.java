package de.hskempten.tabulang.mySql;

import de.hskempten.tabulang.libreOffice.CalcConnection;
import de.hskempten.tabulang.mySql.Models.DbConnectionParameters;

import java.sql.SQLException;

public class MainClass {
    public static void main(String[] args) throws SQLException {

        var dbConnectionParameters = new DbConnectionParameters("85.214.33.119", 3306, "sakila", "db_user", "HsKemptenProjekt2020");
        DatabaseConnection.OpenConnection(dbConnectionParameters);

        var tableName = "film";
        var calcConnection = new CalcConnection("Blubb", "Blubb", "D:\\TestLibreOffice", tableName);
        var query = String.format("SELECT * FROM %s", tableName);

        DatabaseConnection.Export(query, calcConnection, true);
    }
}