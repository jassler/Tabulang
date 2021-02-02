package de.hskempten.tabulang.datatypes.exceptions;

import de.hskempten.tabulang.tokenizer.TextPosition;

public class InterpreterException extends RuntimeException{

    public InterpreterException(String message) {
        super(message);
    }

    public InterpreterException(String message, TextPosition textPosition) {
        super(textPosition + " can not be executed.");
    }

    public InterpreterException(TextPosition term, String allowed, String additionalInfo) {
        super(term + "can not be executed. " + allowed + additionalInfo);
    }

    public InterpreterException(TextPosition term, TextPosition operandInTerm) {
        super(term + "can not be executed because " + operandInTerm.getContent() +" can not be used in operation.");
    }

    public InterpreterException(TextPosition term, String className, String content, String allowedOperands) {
        super(term + "can not be executed because " + className + "(" + content + ") can not be used in operation. " + allowedOperands);
    }

    public InterpreterException(TextPosition term, String className, TextPosition content, String allowedOperands) {
        super(term + "can not be executed because " + className + "(" + content + ") can not be used in operation. " + allowedOperands);
    }

    public InterpreterException(TextPosition term, String className, TextPosition operandInTerm) {
        super(term + "can not be executed because " + className + "(" + operandInTerm.getContent() + ") can not be used in operation. ");
    }

    public InterpreterException(TextPosition term, String className, TextPosition operandInTerm, String additionalInfo, String allowedOperands) {
        super(term + "can not be executed because " + className + "(" + operandInTerm.getContent() + ") can not be used in operation. " + additionalInfo + allowedOperands);
    }

    public InterpreterException(String message, String allowed) {
        super("Operation '" + message + "can not be executed." + allowed);
    }

    public InterpreterException(TextPosition term, String allowed) {
        super(term + "can not be executed. " + allowed);
    }
}
