package de.hskempten.tabulang.importFromSql;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

@SuppressWarnings("InstantiationOfUtilityClass")
public class DatabaseConnection {
    private static DatabaseConnection instance;
    private static Connection connection;
    private static Statement statement;

    private DatabaseConnection() {
        try {
            connection = DriverManager.getConnection(StaticResources.connectionString);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void OpenConnection() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        else if (connection.isClosed()) {
            instance = new DatabaseConnection();
        }
    }

    public static void CloseConnection() throws SQLException {
        statement.close();
        connection.close();
    }

    public static <T> ArrayList<T> SelectStatement(String query, Class<T> obj){
        try {
            statement = connection.createStatement();
            var resultSet = statement.executeQuery(query);
            var returnList = Parse(resultSet, obj);
            statement.close();
            return returnList;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static <T> ArrayList<T> Parse(ResultSet rs, Class<T> obj) {
        try {
            var arrayList = new ArrayList<T>();
            var metaData = rs.getMetaData();
            int count = metaData.getColumnCount();
            while (rs.next()) {
                T newInstance = obj.getDeclaredConstructor().newInstance();
                for (int i = 1; i <= count; i++) {
                    var originalName = metaData.getColumnName(i).toLowerCase();
                    var name = toJavaField(originalName);
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

    private static String toJavaField(String str) {
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
}