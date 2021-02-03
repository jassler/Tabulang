package de.hskempten.tabulang.datatypes.exceptions;

import de.hskempten.tabulang.tokenizer.TextPosition;

public class IllegalNumberArgumentException extends InterpreterException{
    public IllegalNumberArgumentException(String message) {
        super(message, "Allowed operands: Number.");
    }

    public IllegalNumberArgumentException(TextPosition term, String termClassName, String termContent) {
        super(term, termClassName, termContent, "Allowed operands: Number.");
    }

    public IllegalNumberArgumentException(TextPosition term, String additionalInfo) {
        super(term, "Allowed operands: Number.", additionalInfo);
    }

    public IllegalNumberArgumentException(TextPosition term, TextPosition firstOperandInTerm, String firstOperandClass, TextPosition secondOperandInTerm, String secondOperandClass) {
        super(term, "Allowed operands: Number. Got: " + firstOperandClass + "(" + firstOperandInTerm.getContent() + ") and " + secondOperandClass + "(" + secondOperandInTerm.getContent() + ").");
    }

    public IllegalNumberArgumentException(TextPosition textPosition, String simpleName, TextPosition textPosition1) {
        super(textPosition, simpleName, textPosition1, "Allowed operands: Number.");
    }
}
