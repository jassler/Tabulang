package de.hskempten.tabulang.astNodes.term.arithmetic;

import de.hskempten.tabulang.astNodes.term.TermNode;
import de.hskempten.tabulang.tokenizer.TextPosition;

public abstract class ArithmeticNode extends TermNode {

    public ArithmeticNode(TextPosition textPosition) {
        super(textPosition);
    }
}

