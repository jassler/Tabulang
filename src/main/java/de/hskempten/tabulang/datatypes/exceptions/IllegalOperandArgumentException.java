package de.hskempten.tabulang.datatypes.exceptions;

public class IllegalOperandArgumentException extends RuntimeException{
    public IllegalOperandArgumentException(String message) {
        super(message);
    }
}
