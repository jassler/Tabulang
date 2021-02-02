package de.hskempten.tabulang.astNodes.predicate;

import de.hskempten.tabulang.astNodes.Node;
import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.datatypes.exceptions.IllegalBooleanArgumentException;
import de.hskempten.tabulang.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public abstract class PredicateNode extends Node {

    public PredicateNode(TextPosition textPosition) {
        super(textPosition);
    }

    public InternalBoolean verifyAndReturnBoolean(Node node, Interpretation interpretation) {
        Object o = node.evaluateNode(interpretation);
        if (!(o instanceof InternalBoolean internalBoolean)) {
            throw new IllegalBooleanArgumentException(getTextPosition(), o.getClass().getSimpleName(), node.getTextPosition().getContent());
        }
        return internalBoolean;
    }
}
