package de.hskempten.tabulang.datatypes.exceptions;

public class VariableNotDeclaredException extends InterpreterException{
    public VariableNotDeclaredException(String msg) {
        super("Identifier '"+ msg + "' has not been declared.");
    }
}
