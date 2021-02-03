package de.hskempten.tabulang.astNodes.predicate;

import de.hskempten.tabulang.interpreter.Interpretation;
import de.hskempten.tabulang.astNodes.Node;
import de.hskempten.tabulang.datatypes.InternalBoolean;
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

    /**
     * Returns negation of a evaluated node.
     *
     * @return InternalBoolean with value true if evaluated node value false,
     * InternalBoolean with value false otherwise.
     */
    @Override
    public Object evaluateNode(Interpretation interpretation) {
        InternalBoolean internalBoolean = node.verifyAndReturnBoolean(interpretation);
        return new InternalBoolean(!(internalBoolean.getBoolean()));
    }

    @Override
    public String toString() {
        return "not " + node;
    }
}
