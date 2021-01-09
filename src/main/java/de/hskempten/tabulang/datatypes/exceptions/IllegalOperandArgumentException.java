package de.hskempten.tabulang.datatypes.exceptions;

import de.hskempten.tabulang.tokenizer.TextPosition;

public class IllegalOperandArgumentException extends RuntimeException{

    public IllegalOperandArgumentException(String message) {
        super(message);
    }

    public IllegalOperandArgumentException(String message, TextPosition textPosition) {
        super(textPosition + " can not be executed.");
    }

    public IllegalOperandArgumentException(TextPosition term, String allowed, String additionalInfo) {
        super(term + "can not be executed. " + allowed + additionalInfo);
    }

    public IllegalOperandArgumentException(TextPosition term, TextPosition operandInTerm) {
        super(term + "can not be executed because " + operandInTerm.getContent() +" can not be used in operation.");
    }

    public IllegalOperandArgumentException(TextPosition term, String className, String content, String allowedOperands) {
        super(term + "can not be executed because " + className + "(" + content + ") can not be used in operation. " + allowedOperands);
    }

    public IllegalOperandArgumentException(TextPosition term, String className, TextPosition content, String allowedOperands) {
        super(term + "can not be executed because " + className + "(" + content + ") can not be used in operation. " + allowedOperands);
    }

    public IllegalOperandArgumentException(TextPosition term, String className, TextPosition operandInTerm) {
        super(term + "can not be executed because " + className + "(" + operandInTerm.getContent() + ") can not be used in operation. ");
    }

    public IllegalOperandArgumentException(TextPosition term, String className, TextPosition operandInTerm, String additionalInfo, String allowedOperands) {
        super(term + "can not be executed because " + className + "(" + operandInTerm.getContent() + ") can not be used in operation. " + additionalInfo + allowedOperands);
    }

    public IllegalOperandArgumentException(String message, String allowed) {
        super("Operation '" + message + "can not be executed." + allowed);
    }

    public IllegalOperandArgumentException(TextPosition term, String allowed) {
        super(term + "can not be executed. " + allowed);
    }
}
