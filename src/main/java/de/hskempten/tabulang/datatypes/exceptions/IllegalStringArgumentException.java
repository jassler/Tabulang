package de.hskempten.tabulang.datatypes.exceptions;

import de.hskempten.tabulang.tokenizer.TextPosition;

public class IllegalStringArgumentException extends InterpreterException {

    public IllegalStringArgumentException(TextPosition term, String className, String content) {
        super(term, className, content, "Allowed operands: String.");
    }
}
