package de.hskempten.tabulang.mySql;
import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.libreOffice.OdsExportService;
import de.hskempten.tabulang.mySql.Models.MSqlConnectionParameters;
import de.hskempten.tabulang.mySql.Models.MSqlTableContent;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public class DatabaseConnection {
    /* PROPERTIES */
    private static DatabaseConnection _instance;
    private static Connection _connection;
    private static Statement _statement;
    private static MSqlConnectionParameters _parameters;

    /* CONSTRUCTOR */

    /**
     * See {@link DatabaseConnection#OpenConnection(MSqlConnectionParameters)}
     *
     * Create a new instance of DatabaseConnection
     * @param parameters Contains all necessary information for a stable connection
     */

    private DatabaseConnection(MSqlConnectionParameters parameters) {
        try {
            _parameters = parameters;
            _connection = DriverManager.getConnection(CreateConnectionString(_parameters));
        }
        catch (SQLException e) {
            // e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
        }
    }

    /* PUBLIC METHODS */

    /**
     * Open a MySQL connection as Singleton
     * @param parameters Contains all necessary information for a stable connection
     */

    public static void OpenConnection(MSqlConnectionParameters parameters){
        try {
            _parameters = parameters;
            if (_instance == null) {
                _instance = new DatabaseConnection(_parameters);
            }
            else if (_connection.isClosed()) {
                _instance = new DatabaseConnection(_parameters);
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
        }
    }

    /**
     * Close a MySQL connection
     */

    public static void CloseConnection() {
        try {
            _connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Export a MySQL table to an *.ods file
     *
     * @param query             SQL query which should be exported
     * @param odsExportService  Instance of {@link OdsExportService}
     * @param path              Specification of the path where the ods file should be saved
     * @param fileName          File name of the table to be exported (without specifying ods)
     */

    public static void ExportToFile(String query, OdsExportService odsExportService, String path, String fileName){
        odsExportService.InstantlyExportToFile(Objects.requireNonNull(ExportCore(query)), path, fileName);
    }

    /**
     * Export a MySQL table to an instance of {@link Table}
     *
     * @param query SQL query which should be exported
     * @return {@link Table} of strings
     */

    public static Table<String> ExportAsTable(String query){
        var sqlTableContent = ExportCore(query);
        assert sqlTableContent != null;
        return new Table<>(sqlTableContent.get_headlines(), sqlTableContent.get_content());
    }

    /**
     * Insert into a table in the MySQL database from a table of the language
     *
     * @param table         Instance of {@link Table} with all headlines and contents
     * @param sqlTableName  MySQL table name where the record should be added
     */

    public static void ImportFromTable(Table table, String sqlTableName) {
        try {
            var content = new ArrayList<ArrayList<String>>();
            for(var item : table){
                var contentRows = new ArrayList<String>();
                for(var cell : (Tuple<String>) item){
                    contentRows.add(cell.getData());
                }
                content.add(contentRows);
            }
            ImportCore(new MSqlTableContent(sqlTableName, ReplaceHeadline(table), content));
        } catch(Exception e){
          e.printStackTrace();
        }

    }

    /* PRIVATE METHODS */

    /**
     * Insert into a table in the MySQL database
     *
     * @param sqlTableContent Contains all headlines content-rows
     */

    private static void ImportCore(MSqlTableContent sqlTableContent){
        try {
            if(!DatabaseExists(sqlTableContent)) throw new SQLException("Database not exist");
            var selectQuery = String.format("SELECT * FROM %s", sqlTableContent.get_dbName());
            _statement = _connection.createStatement();
            var resultSet = _statement.executeQuery(selectQuery);
            var keys = GetContentOfArray(sqlTableContent.get_headlines(), GetColumnTypes(resultSet.getMetaData()), true);
            for(var item : sqlTableContent.get_content()){
                var values = GetContentOfArray(item, GetColumnTypes(resultSet.getMetaData()), false);
                var query = String.format("INSERT INTO %s (%s) VALUES (%s);", sqlTableContent.get_dbName(), keys, values);
                _statement = _connection.createStatement();
                _statement.executeUpdate(query);
                _statement.close();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Helper function for {@link DatabaseConnection#ExportToFile(String, OdsExportService, String, String)}
     * Helper function for {@link DatabaseConnection#ExportAsTable(String)}
     *
     * Core function for export service
     *
     * @param query MySQL statement (query)
     * @return An instance of the object {@link MSqlTableContent}
     */

    private static MSqlTableContent ExportCore(String query) {
        try {
            _statement = _connection.createStatement();
            var resultSet = _statement.executeQuery(query);
            var headlines = GetHeadlines(resultSet.getMetaData());
            var values = GetColumnValues(headlines, resultSet);
            var sqlTableContent = new MSqlTableContent(GetTableName(query), headlines, values);
            _statement.close();
            return sqlTableContent;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Helper function for {@link DatabaseConnection#ImportCore(MSqlTableContent)}
     *
     * Check, if a database exists or not
     *
     * @param sqlTableContent Instance of {@link MSqlTableContent} which contains all information
     * @return True or false (Database exists or not)
     */

    private static boolean DatabaseExists(MSqlTableContent sqlTableContent){
        try {
            _statement = _connection.createStatement();
            var resultSet = _statement.executeQuery("SELECT * FROM " + sqlTableContent.get_dbName());
            _statement.close();
            var headlinesFromObject = sqlTableContent.get_headlines();
            var headlinesFromDatabase = GetHeadlines(resultSet.getMetaData());
            if(headlinesFromObject.size() != headlinesFromDatabase.size()) return false;
            for(var i = 0; i < headlinesFromDatabase.size(); i++){
                if(!(headlinesFromDatabase.get(i).equals(headlinesFromObject.get(i)))) return false;
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Helper function for {@link DatabaseConnection#ImportCore(MSqlTableContent)}
     *
     * Remove every blank from each headlines
     *
     * @param table Instance of {@link Table} which contains all headlines and contents
     * @return The modified ArrayList of headlines (Strings)
     */

    private static ArrayList<String> ReplaceHeadline(Table table){
        var replacedItems = new ArrayList<String>();
        table
                .getColNames()
                .getNames()
                .stream()
                .forEach(headline -> replacedItems.add(headline.replace(" ", "").toLowerCase()));
        return replacedItems;
    }

    /**
     * Helper function for {@link DatabaseConnection#ImportCore(MSqlTableContent)}
     *
     * @param arr           Values as String (e. g. headlines)
     * @param columnTypes   Types of each value
     * @param headline      Flag, to differentiate if the current row is a headline or not
     * @return              String, to complete the MySQL statement
     */

    private static String GetContentOfArray(ArrayList<String> arr, ArrayList<String> columnTypes, boolean headline){
        var stringBuilder = new StringBuilder();
        var endIndex = arr.size();

        for(var i = 0; i < endIndex; i++){
            if (columnTypes.get(i + 1).equals("VARCHAR") && !headline) {
                arr.set(i, String.format("\"%s\"", arr.get(i)));
            }
            stringBuilder.append(arr.get(i));
            if(i != endIndex - 1){ stringBuilder.append(", "); }
        }
        return stringBuilder.toString();
    }

    /**
     * Helper function for {@link DatabaseConnection#ExportCore(String)}
     *
     * Search the table name from a MySQL statement
     *
     * @param query MySQL statement as a String
     * @return      Filtered table name
     */

    private static String GetTableName(String query) {
        var charArray = query.substring(query.indexOf("FROM") + 5).toCharArray();
        StringBuilder tableName = new StringBuilder();

        for (char c : charArray) {
            if (c == ' ') {
                break;
            }
            tableName.append(c);
        }
        return tableName.toString();
    }

    /**
     * Helper function for {@link DatabaseConnection#ExportCore(String)}
     *
     * Return a list of content from a specific MySQL statement (filtered by column names)
     *
     * @param names     Name of the headlines
     * @param resultSet ResultSet which contains all data from a table
     * @return          An ArrayList of an ArrayList with string, which contains all contents (order by headlines)
     */

    private static ArrayList<ArrayList<String>> GetColumnValues(ArrayList<String> names, ResultSet resultSet) {
        try {
            var returnList = new ArrayList<ArrayList<String>>();
            while (resultSet.next())
            {
                var tempList = new ArrayList<String>();
                for(var key : names){
                    tempList.add(resultSet.getString(key));
                }
                returnList.add(tempList);
            }
            return returnList;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Helper function for {@link DatabaseConnection#ExportCore(String)}
     * Helper function for {@link DatabaseConnection#DatabaseExists(MSqlTableContent)}
     *
     * Filter the table headlines from a ResultSetMetaData object
     *
     * @param metaData  ResultSetMetaData object as a result from a MySQL statement
     * @return          A list of Strings with the column names (headlines) from a table
     */

    private static ArrayList<String> GetHeadlines(ResultSetMetaData metaData) {
        try {
            var columnNameList = new ArrayList<String>();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                columnNameList.add(metaData.getColumnName(i));
            }
            return columnNameList;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Helper function for {@link DatabaseConnection#ImportCore(MSqlTableContent)}
     *
     * Get all column types of each column
     *
     * @param metaData  List of table headlines and their specific informatin
     * @return          List of Array which contains all column data types in the correct order
     */

    private static ArrayList<String> GetColumnTypes(ResultSetMetaData metaData){
        try {
            var columnNameList = new ArrayList<String>();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                columnNameList.add(metaData.getColumnTypeName(i));
            }
            return columnNameList;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Helper function for {@link DatabaseConnection#DatabaseConnection(MSqlConnectionParameters)}
     *
     * @param parameters    Contains all necessary information for a stable connection
     * @return              Modified connection string for MySQL connection
     */

    private String CreateConnectionString(MSqlConnectionParameters parameters){
        return String.format("jdbc:mysql://%s:%s/%s?" +
                "user=%s&" +
                "password=%s&" +
                "useUnicode=true&" +
                "useJDBCCompliantTimezoneShift=true&" +
                "useLegacyDatetimeCode=false&" +
                "serverTimezone=UTC", parameters.get_ip(), parameters.get_port(), parameters.get_dbName(), parameters.get_username(), parameters.get_password());
    }
}