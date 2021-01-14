package de.hskempten.tabulang.interpretTest;

import de.hskempten.tabulang.tokenizer.PositionedException;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class InterpreterException extends PositionedException {

    public InterpreterException(String msg, TextPosition position) {
        super(position, msg);
    }

}
