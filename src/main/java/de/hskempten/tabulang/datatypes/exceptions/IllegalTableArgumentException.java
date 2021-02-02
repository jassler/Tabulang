package de.hskempten.tabulang.datatypes.exceptions;

import de.hskempten.tabulang.tokenizer.TextPosition;

public class IllegalTableArgumentException extends InterpreterException{
    public IllegalTableArgumentException(String message) {
        super(message, "Allowed operands: Tables.");
    }

    public IllegalTableArgumentException(TextPosition term, String className, String termContent) {
        super(term, className, termContent, "Allowed operands: Table.");
    }

    public IllegalTableArgumentException(TextPosition term, String className, String termContent, String additionalInfo) {
        super(term, className, termContent, additionalInfo);
    }
}
