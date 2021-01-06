package de.hskempten.tabulang.datatypes.exceptions;

public class IllegalTupleOperandArgumentException extends IllegalOperandArgumentException{
    public IllegalTupleOperandArgumentException(String message) {
        super(message, "Allowed operands: Tables.");
    }
}
