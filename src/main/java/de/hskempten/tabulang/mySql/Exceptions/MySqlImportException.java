package de.hskempten.tabulang.mySql.Exceptions;

public class MySqlImportException extends RuntimeException{
    public MySqlImportException() {}

    public MySqlImportException(String message)
    {
        super(message);
    }
}
