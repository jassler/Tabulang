package de.hskempten.tabulang.mySql.Exceptions;

import java.net.ConnectException;

public class MySqlConnectionException extends RuntimeException {
    public MySqlConnectionException() {}

    public MySqlConnectionException(String message)
    {
        super(message);
    }
}
