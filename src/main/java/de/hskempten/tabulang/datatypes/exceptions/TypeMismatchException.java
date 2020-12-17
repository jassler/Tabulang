package de.hskempten.tabulang.datatypes.exceptions;

public class TypeMismatchException extends RuntimeException{
    public TypeMismatchException(String left, String right) {
        super("Operation needs operands of the same type but got: " + left + " and " + right);
    }
}
