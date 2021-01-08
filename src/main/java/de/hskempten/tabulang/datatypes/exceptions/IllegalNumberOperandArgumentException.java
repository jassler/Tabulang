package de.hskempten.tabulang.datatypes.exceptions;

import de.hskempten.tabulang.tokenizer.TextPosition;

public class IllegalNumberOperandArgumentException extends IllegalOperandArgumentException{
    public IllegalNumberOperandArgumentException(String message) {
        super(message, "Allowed operands: Number.");
    }

    public IllegalNumberOperandArgumentException(TextPosition term, String termClassName, String termContent) {
        super(term, termClassName, termContent, "Allowed operands: Number.");
    }

    public IllegalNumberOperandArgumentException(TextPosition term, String additionalInfo) {
        super(term, "Allowed operands: Number.", additionalInfo);
    }

    public IllegalNumberOperandArgumentException(TextPosition term, TextPosition firstOperandInTerm, String firstOperandClass, TextPosition secondOperandInTerm, String secondOperandClass) {
        super(term, "Allowed operands: Number. Got: " + firstOperandClass + "(" + firstOperandInTerm.getContent() + ") and " + secondOperandClass + "(" + secondOperandInTerm.getContent() + ").");
    }
}
