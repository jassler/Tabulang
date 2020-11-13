package de.hskempten.tabulang.mySql;
import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.libreOffice.CalcConnection;
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
    private DatabaseConnection(MSqlConnectionParameters parameters) {
        try {
            _parameters = parameters;
            _connection = DriverManager.getConnection(CreateConnectionString(_parameters));
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* PUBLIC METHODS */
    public static void OpenConnection(MSqlConnectionParameters parameters) throws SQLException {
        _parameters = parameters;
        if (_instance == null) {
            _instance = new DatabaseConnection(_parameters);
        }
        else if (_connection.isClosed()) {
            _instance = new DatabaseConnection(_parameters);
        }
    }

    public static void CloseConnection() {
        try {
            _connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void ExportToFile(String query, CalcConnection calcConnection){
        calcConnection.Export(Objects.requireNonNull(ExportCore(query)));
    }

    public static Table<String> ExportAsTable(String query){
        var sqlTableContent = ExportCore(query);
        assert sqlTableContent != null;
        return new Table<>(sqlTableContent.get_headlines(), sqlTableContent.get_content());
    }

    public static void Import(MSqlTableContent sqlTableContent){
        try {
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

    /* PRIVATE METHODS */
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

    private static String GetContentOfArray(ArrayList<String> arr, ArrayList<String> columnTypes, boolean headline){
        var stringBuilder = new StringBuilder();
        var endIndex = arr.size();

        for(var i = 0; i < endIndex; i++){
            if (columnTypes.get(i + 1).equals("VARCHAR") && !headline) {
                var item = arr.get(i);
                arr.set(i, String.format("\"%s\"", arr.get(i)));
            }
            stringBuilder.append(arr.get(i));
            if(i != endIndex - 1){ stringBuilder.append(", "); }
        }
        return stringBuilder.toString();
    }

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

    private String CreateConnectionString(MSqlConnectionParameters parameters){
        return String.format("jdbc:mysql://%s:%s/%s?" +
                "user=%s&" +
                "password=%s&" +
                "useUnicode=true&" +
                "useJDBCCompliantTimezoneShift=true&" +
                "useLegacyDatetimeCode=false&" +
                "serverTimezone=UTC", parameters.get_ip(), parameters.get_port(), parameters.get_dbName(), parameters.get_username(), parameters.get_password());
    }

    // PARSE FUNCTION - NOT NECESSARY (02.11.2020)
    /*
    private static <T> ArrayList<T> Parse(ResultSet rs, Class<T> obj) {
        try {
            var arrayList = new ArrayList<T>();
            var metaData = rs.getMetaData();
            int count = metaData.getColumnCount();
            while (rs.next()) {
                T newInstance = obj.getDeclaredConstructor().newInstance();
                for (int i = 1; i <= count; i++) {
                    var originalName = metaData.getColumnName(i).toLowerCase();
                    var name = ToJavaField(originalName);
                    var substring = name.substring(0, 1);
                    var replace = name.replaceFirst(substring, substring.toUpperCase());
                    Class<?> type;
                    try {
                        type = obj.getDeclaredField(originalName).getType();
                    } catch (NoSuchFieldException e) {
                        continue;
                    }

                    var method = obj.getMethod("set" + replace, type);
                    if (type.isAssignableFrom(String.class)) {
                        method.invoke(newInstance, rs.getString(i));
                    } else if (type.isAssignableFrom(byte.class) || type.isAssignableFrom(Byte.class)) {
                        method.invoke(newInstance, rs.getByte(i));
                    } else if (type.isAssignableFrom(short.class) || type.isAssignableFrom(Short.class)) {
                        method.invoke(newInstance, rs.getShort(i));
                    } else if (type.isAssignableFrom(int.class) || type.isAssignableFrom(Integer.class)) {
                        method.invoke(newInstance, rs.getInt(i));
                    } else if (type.isAssignableFrom(long.class) || type.isAssignableFrom(Long.class)) {
                        method.invoke(newInstance, rs.getLong(i));
                    } else if (type.isAssignableFrom(float.class) || type.isAssignableFrom(Float.class)) {
                        method.invoke(newInstance, rs.getFloat(i));
                    } else if (type.isAssignableFrom(double.class) || type.isAssignableFrom(Double.class)) {
                        method.invoke(newInstance, rs.getDouble(i));
                    } else if (type.isAssignableFrom(BigDecimal.class)) {
                        method.invoke(newInstance, rs.getBigDecimal(i));
                    } else if (type.isAssignableFrom(boolean.class) || type.isAssignableFrom(Boolean.class)) {
                        method.invoke(newInstance, rs.getBoolean(i));
                    } else if (type.isAssignableFrom(Date.class)) {
                        method.invoke(newInstance, rs.getDate(i));
                    }
                }
                arrayList.add(newInstance);
            }
            return arrayList;
        }
        catch (InstantiationException | IllegalAccessException | SQLException | SecurityException | NoSuchMethodException | IllegalArgumentException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String ToJavaField(String str) {
        var split = str.split("_");
        var builder = new StringBuilder();
        builder.append(split[0]);// Concatenate first character
        if (split.length > 1) {
            for (int i = 1; i < split.length; i++) {
                // Remove underscores and capitalize initial
                var string = split[i];
                var substring = string.substring(0, 1);
                split[i] = string.replaceFirst(substring, substring.toUpperCase());
                builder.append(split[i]);
            }
        }
        return builder.toString();
    }
    */
}