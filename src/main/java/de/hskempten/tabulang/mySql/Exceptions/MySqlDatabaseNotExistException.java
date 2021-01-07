package de.hskempten.tabulang.mySql.Exceptions;

public class MySqlDatabaseNotExistException extends RuntimeException{
    public MySqlDatabaseNotExistException() {}

    public MySqlDatabaseNotExistException(String message)
    {
        super(message);
    }
}
