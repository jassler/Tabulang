package de.hskempten.tabulang.datatypes.exceptions;

public class IllegalBooleanOperandArgumentException extends IllegalOperandArgumentException{
    public IllegalBooleanOperandArgumentException(String message) {
        super(message, " Allowed operands: Boolean.");
    }
}
