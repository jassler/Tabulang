package de.hskempten.tabulang.datatypes.exceptions;

public class VariableNotInitializedException extends RuntimeException{
    public VariableNotInitializedException(String msg) {
        super("Identifier '"+ msg + "' has not been initialized");
    }
}
