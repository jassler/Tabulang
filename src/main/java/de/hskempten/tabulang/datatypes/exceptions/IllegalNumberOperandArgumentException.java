package de.hskempten.tabulang.datatypes.exceptions;

public class IllegalNumberOperandArgumentException extends IllegalOperandArgumentException{
    public IllegalNumberOperandArgumentException(String message) {
        super(message, " Allowed operands: Numbers.");
    }
}
