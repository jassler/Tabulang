package de.hskempten.tabulang.mySql;

import de.hskempten.tabulang.libreOffice.CalcConnection;
import de.hskempten.tabulang.mySql.Models.MSqlConnectionParameters;
import de.hskempten.tabulang.mySql.Models.MSqlTableContent;

import javax.xml.crypto.Data;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainClass {
    private static final String _ip = "85.214.33.119";
    private static final int _port = 3306;
    private static final String _dbName = "sakila";
    private static final String _dbUser = "db_user";
    private static final String _dbPassword = "HsKemptenProjekt2020";
    private static final String _sqlTableName = "test_db";
    private static final String _instanceName = "Blubb";
    private static final String _calcTableName = "Blubb";
    private static final String _path = "D:\\TestLibreOffice";
    private static final String _fileName = "test";

    public static void main(String[] args) throws SQLException {
        // DECLARATION FOR DATABASE CONNECTION
        var dbConnectionParameters = new MSqlConnectionParameters(_ip, _port, _dbName, _dbUser, _dbPassword);

        // DECLARATION CONNECTION TO LIBRE OFFICE
        var calcConnection = new CalcConnection(_instanceName, _calcTableName, _path, _fileName);

        // OPEN DATABASE CONNECTION
        DatabaseConnection.OpenConnection(dbConnectionParameters);

        // SQL QUERY DECLARATION
        var query = String.format("SELECT * FROM %s", _sqlTableName);
        //var queryJoin = String.format("SELECT f.title, f.description, l.name FROM sakila.film f JOIN sakila.language l;");

        // TESTLIST FOR SQL-IMPORT
        /*
        var headlines = new ArrayList<String>();
        headlines.add("name");
        headlines.add("active");
        headlines.add("age");
        var values = new ArrayList<ArrayList<String>>();
        var first_value = new ArrayList<String>();
        first_value.add("Michi");
        first_value.add("true");
        first_value.add("34");
        values.add(first_value);
         */

        // INSERT DATA INTO DATABASE
        //var insertObject = new MSqlTableContent("test_db", headlines, values);
        //DatabaseConnection.Import(insertObject);

        // EXPORT DATA FROM DATABASE
        //DatabaseConnection.Export(query, calcConnection, true);
        //System.out.println(DatabaseConnection.ExportAsTable(query));
        //DatabaseConnection.ExportToFile(query, calcConnection);

        // OPEN FILE AND CALL IMPORT FUNCTION
        calcConnection.Import(new File(_path, _fileName+".ods").toString());
    }
}