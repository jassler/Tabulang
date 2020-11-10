package de.hskempten.tabulang.mySql;

import de.hskempten.tabulang.libreOffice.CalcConnection;

import java.io.File;

public class MainClass {
    private static final String _ip = "85.214.33.119";
    private static final int _port = 3306;
    private static final String _dbName = "sakila";
    private static final String _dbUser = "db_user";
    private static final String _dbPassword = "HsKemptenProjekt2020";
    private static final String _sqlTableName = "film";
    private static final String _instanceName = "Blubb";
    private static final String _calcTableName = "Blubb";
    private static final String _path = "D:\\TestLibreOffice";
    private static final String _fileName = "test";

    public static void main(String[] args) {
        /* var dbConnectionParameters = new MSqlConnectionParameters(_ip, _port, _dbName, _dbUser, _dbPassword);
        var query = String.format("SELECT * FROM %s", _sqlTableName);
        var queryJoin = String.format("SELECT f.title, f.description, l.name FROM sakila.film f JOIN sakila.language l;");
        DatabaseConnection.Export(query, calcConnection, true);

         */
        var calcConnection = new CalcConnection(_instanceName, _calcTableName, _path, _fileName);
        var path = new File(_path, _fileName+".ods").toString();
        calcConnection.Import(path);
    }
}