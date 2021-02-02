package de.hskempten.tabulang.datatypes.exceptions;

import de.hskempten.tabulang.tokenizer.TextPosition;

public class IllegalTupleArgumentException extends InterpreterException{
    public IllegalTupleArgumentException(String message) {
        super(message, "Allowed operands: Tuple or Table.");
    }

    public IllegalTupleArgumentException(TextPosition term, String className, String termContent) {
        super(term, className, termContent, "Allowed operands: Tuple or Table.");
    }

    public IllegalTupleArgumentException(TextPosition term, String className, TextPosition operandInTerm, String additionalInfo) {
        super(term, className, operandInTerm, additionalInfo, "Allowed operands: Tuple or Table.");
    }
}
