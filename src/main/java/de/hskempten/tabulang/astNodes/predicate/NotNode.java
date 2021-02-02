package de.hskempten.tabulang.astNodes.predicate;

import de.hskempten.tabulang.astNodes.Node;
import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.datatypes.exceptions.IllegalBooleanArgumentException;
import de.hskempten.tabulang.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class NotNode extends PredicateNode {
    private Node node;

    public NotNode(Node node, TextPosition textPosition) {
        super(textPosition);
        this.node = node;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object pred = node.evaluateNode(interpretation);
        if (!(pred instanceof InternalBoolean internalBoolean)) {
            throw new IllegalBooleanArgumentException(toString());
        }
        return new InternalBoolean(!(internalBoolean.getBoolean()));
    }

    @Override
    public String toString() {
        return "not " + node;
    }
}
