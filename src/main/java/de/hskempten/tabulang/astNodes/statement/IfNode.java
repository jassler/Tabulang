package de.hskempten.tabulang.astNodes.statement;

import de.hskempten.tabulang.astNodes.Node;
import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.datatypes.exceptions.IllegalBooleanArgumentException;
import de.hskempten.tabulang.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class IfNode extends BinaryStatementNode {
    public IfNode(Node leftNode, Node rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object left = getLeftNode().evaluateNode(interpretation);
        if (!(left instanceof InternalBoolean bool)) {
            throw new IllegalBooleanArgumentException(toString());
        }
        if (bool.getBoolean()) {
            return getRightNode().evaluateNode(interpretation);
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "if(" + getLeftNode() + ") " + getRightNode();
    }
}
