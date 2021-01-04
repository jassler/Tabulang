package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.datatypes.exceptions.IllegalBooleanOperandArgumentException;
import de.hskempten.tabulang.datatypes.exceptions.IllegalOperandArgumentException;
import de.hskempten.tabulang.interpretTest.Interpretation;

public class NotNode extends PredicateNode{
    private Node node;

    public NotNode(Node node) {
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
        if (!(pred instanceof InternalBoolean bool)) {
            throw new IllegalBooleanOperandArgumentException(toString());
        }
        return !(bool.getaBoolean());
    }

    @Override
    public String toString() {
        return "not " + node;
    }
}
