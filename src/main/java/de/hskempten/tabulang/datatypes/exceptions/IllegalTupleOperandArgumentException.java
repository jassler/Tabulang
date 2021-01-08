package de.hskempten.tabulang.datatypes.exceptions;

import de.hskempten.tabulang.tokenizer.TextPosition;

public class IllegalTupleOperandArgumentException extends IllegalOperandArgumentException{
    public IllegalTupleOperandArgumentException(String message) {
        super(message, "Allowed operands: Tuple or Table.");
    }

    public IllegalTupleOperandArgumentException(TextPosition term, String className, String termContent) {
        super(term, className, termContent, "Allowed operands: Tuple or Table.");
    }

    public IllegalTupleOperandArgumentException(TextPosition term, String className, TextPosition operandInTerm, String additionalInfo) {
        super(term, className, operandInTerm, additionalInfo, "Allowed operands: Tuple or Table.");
    }
}
