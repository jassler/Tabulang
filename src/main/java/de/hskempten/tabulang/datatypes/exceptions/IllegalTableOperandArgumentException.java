package de.hskempten.tabulang.datatypes.exceptions;

import de.hskempten.tabulang.tokenizer.TextPosition;

public class IllegalTableOperandArgumentException extends IllegalOperandArgumentException{
    public IllegalTableOperandArgumentException(String message) {
        super(message, "Allowed operands: Tables.");
    }

    public IllegalTableOperandArgumentException(TextPosition term, String className, String termContent) {
        super(term, className, termContent, "Allowed operands: Table.");
    }
}
