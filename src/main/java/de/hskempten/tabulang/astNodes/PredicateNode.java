package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.tokenizer.TextPosition;

public abstract class PredicateNode extends Node{

    public PredicateNode(TextPosition textPosition) {
        super(textPosition);
    }
}
