package de.hskempten.tabulang.mySql.Exceptions;

public class ValueNotExistException extends RuntimeException{
    public ValueNotExistException() {}

    public ValueNotExistException(String message)
    {
        super(message);
    }
}
