package de.hskempten.tabulang.mySql.Exceptions;

public class MySqlExportException extends RuntimeException{
    public MySqlExportException() {}

    public MySqlExportException(String message)
    {
        super(message);
    }
}
