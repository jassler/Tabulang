package de.hskempten.tabulang.datatypes.exceptions;

import de.hskempten.tabulang.tokenizer.TextPosition;

public class IllegalBooleanArgumentException extends InterpreterException{
    public IllegalBooleanArgumentException(String message) {
        super(message, " Allowed operands: Boolean.");
    }

    public IllegalBooleanArgumentException(TextPosition term, String className, String content) {
        super(term, className, content, "Allowed operands: Boolean.");
    }
}
