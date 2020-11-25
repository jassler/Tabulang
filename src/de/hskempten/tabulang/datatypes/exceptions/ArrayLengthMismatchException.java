package de.hskempten.tabulang.datatypes.exceptions;

public class ArrayLengthMismatchException extends DataTypeException {

    public ArrayLengthMismatchException(int l1, int l2) {
        super("Array lengths don't match, got an array of length " + l1
                + " and another of length " + l2 + ". They should be the same.");
    }

    public ArrayLengthMismatchException(String message) {
        super(message);
    }

}
