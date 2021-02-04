package de.hskempten.tabulang.astNodes.predicate;

import de.hskempten.tabulang.astNodes.Node;
import de.hskempten.tabulang.tokenizer.TextPosition;

public abstract class PredicateNode extends Node {

    public PredicateNode(TextPosition textPosition) {
        super(textPosition);
    }
}
