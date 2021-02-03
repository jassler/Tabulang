package de.hskempten.tabulang.astNodes.statement;

import de.hskempten.tabulang.astNodes.Node;
import de.hskempten.tabulang.tokenizer.TextPosition;

public abstract class StatementNode extends Node {

    public StatementNode(TextPosition textPosition) {
        super(textPosition);
    }
}
