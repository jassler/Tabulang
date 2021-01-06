package de.hskempten.tabulang.datatypes.exceptions;

import de.hskempten.tabulang.tokenizer.TextPosition;

public class IllegalOperandArgumentException extends RuntimeException{

    public IllegalOperandArgumentException(String message) {
        super("Operation '" + message + "' can not be executed.");
    }

    public IllegalOperandArgumentException(String message, TextPosition textPosition) {
        super(textPosition + " can not be executed.");
    }

    public IllegalOperandArgumentException(TextPosition term, TextPosition operandInTerm) {
        super(term + " can not be executed because " + operandInTerm.getContent() +" can not be used in operation.");
    }

    public IllegalOperandArgumentException(String message, String allowed) {
        super("Operation '" + message + "' can not be executed." + allowed);
    }
}
