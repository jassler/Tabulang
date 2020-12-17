package de.hskempten.tabulang.datatypes.exceptions;

public class VariableAlreadyDefinedException extends RuntimeException{

    public VariableAlreadyDefinedException(String varName) {
        super("Identifier '" + varName + "' already defined");
    }

}
