package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.tokenizer.TextPosition;

public abstract class StatementNode extends Node {

    public StatementNode(TextPosition textPosition) {
        super(textPosition);
    }
}
