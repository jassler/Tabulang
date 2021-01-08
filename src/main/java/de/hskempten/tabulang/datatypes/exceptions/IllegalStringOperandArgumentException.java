package de.hskempten.tabulang.datatypes.exceptions;

import de.hskempten.tabulang.tokenizer.TextPosition;

public class IllegalStringOperandArgumentException extends IllegalOperandArgumentException{
    public IllegalStringOperandArgumentException(TextPosition term, String className, String termContent) {
        super(term, className, termContent, "Allowed operands: String.");
    }
}
