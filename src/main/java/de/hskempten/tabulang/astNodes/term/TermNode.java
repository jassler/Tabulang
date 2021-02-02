package de.hskempten.tabulang.astNodes.term;

import de.hskempten.tabulang.astNodes.Node;
import de.hskempten.tabulang.tokenizer.TextPosition;

public abstract class TermNode extends Node {

    public TermNode() {
    }

    public TermNode(TextPosition textPosition) {
        super(textPosition);
    }


}
