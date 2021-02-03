package de.hskempten.tabulang.datatypes.exceptions;

public class VariableAlreadyDefinedException extends InterpreterException{

    public VariableAlreadyDefinedException(String varName) {
        super("Identifier '" + varName + "' already defined");
    }

}
