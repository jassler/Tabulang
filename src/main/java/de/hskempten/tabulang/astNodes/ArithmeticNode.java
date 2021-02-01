package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.tokenizer.TextPosition;

public abstract class ArithmeticNode extends TermNode {

    public ArithmeticNode(TextPosition textPosition) {
        super(textPosition);
    }
}

