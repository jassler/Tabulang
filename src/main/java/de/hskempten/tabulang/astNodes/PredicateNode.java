package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.datatypes.exceptions.IllegalBooleanOperandArgumentException;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public abstract class PredicateNode extends Node {

    public PredicateNode(TextPosition textPosition) {
        super(textPosition);
    }

    public InternalBoolean verifyAndReturnBoolean(Node node, Interpretation interpretation) {
        Object o = node.evaluateNode(interpretation);
        if (!(o instanceof InternalBoolean internalBoolean)) {
            throw new IllegalBooleanOperandArgumentException(getTextPosition(), o.getClass().getSimpleName(), node.getTextPosition().getContent());
        }
        return internalBoolean;
    }
}
