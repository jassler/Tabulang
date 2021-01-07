package de.hskempten.tabulang.mySql.Exceptions;

import java.net.ConnectException;

public class MySqlConnectionException extends ConnectException {
    public MySqlConnectionException() {}

    public MySqlConnectionException(String message)
    {
        super(message);
    }
}
