package de.hskempten.tabulang.datatypes.exceptions;

import de.hskempten.tabulang.tokenizer.TextPosition;

public class IllegalBooleanOperandArgumentException extends IllegalOperandArgumentException{
    public IllegalBooleanOperandArgumentException(String message) {
        super(message, " Allowed operands: Boolean.");
    }

    public IllegalBooleanOperandArgumentException(TextPosition term, String className, String content) {
        super(term, className, content, "Allowed operands: Boolean.");
    }
}
