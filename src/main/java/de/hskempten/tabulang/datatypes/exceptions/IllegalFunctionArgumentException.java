package de.hskempten.tabulang.datatypes.exceptions;

import de.hskempten.tabulang.tokenizer.TextPosition;

public class IllegalFunctionArgumentException extends InterpreterException{
    public IllegalFunctionArgumentException(String message) {
        super(message);
    }

    public IllegalFunctionArgumentException(String message, TextPosition textPosition) {
        super(message, textPosition);
    }

    public IllegalFunctionArgumentException(TextPosition term, String allowed, String additionalInfo) {
        super(term, allowed, additionalInfo);
    }

    public IllegalFunctionArgumentException(TextPosition term, TextPosition operandInTerm) {
        super(term, operandInTerm);
    }

    public IllegalFunctionArgumentException(TextPosition term, String className, String content, String allowedOperands) {
        super(term, className, content, allowedOperands);
    }

    public IllegalFunctionArgumentException(TextPosition term, String className, TextPosition content, String allowedOperands) {
        super(term, className, content, allowedOperands);
    }

    public IllegalFunctionArgumentException(TextPosition term, String className, TextPosition operandInTerm) {
        super(term, className, operandInTerm);
    }

    public IllegalFunctionArgumentException(TextPosition term, String className, TextPosition operandInTerm, String additionalInfo, String allowedOperands) {
        super(term, className, operandInTerm, additionalInfo, allowedOperands);
    }

    public IllegalFunctionArgumentException(String message, String allowed) {
        super(message, allowed);
    }

    public IllegalFunctionArgumentException(TextPosition term, String allowed) {
        super(term, allowed);
    }
}
