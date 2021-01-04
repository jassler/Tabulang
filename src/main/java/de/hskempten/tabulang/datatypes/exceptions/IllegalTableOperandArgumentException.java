package de.hskempten.tabulang.datatypes.exceptions;

public class IllegalTableOperandArgumentException extends IllegalOperandArgumentException{
    public IllegalTableOperandArgumentException(String message) {
        super(message, "Allowed operands: Tables.");
    }
}
