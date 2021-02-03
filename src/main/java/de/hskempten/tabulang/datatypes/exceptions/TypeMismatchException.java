package de.hskempten.tabulang.datatypes.exceptions;

import de.hskempten.tabulang.tokenizer.TextPosition;

public class TypeMismatchException extends InterpreterException{
    public TypeMismatchException(TextPosition textPosition) {
        super(textPosition + " can not be executed because operands are of non-compatible types.");
    }
}
