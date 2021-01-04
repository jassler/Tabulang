package de.hskempten.tabulang.datatypes.exceptions;

public class IllegalOperandArgumentException extends RuntimeException{
    public IllegalOperandArgumentException(String message) {
        super("Operation '" + message + "' can not be executed.");
    }

    public IllegalOperandArgumentException(String message, String allowed) {
        super("Operation '" + message + "' can not be executed." + allowed);
    }
}
