package de.hskempten.tabulang.astNodes.statement;

import de.hskempten.tabulang.astNodes.Node;
import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.datatypes.exceptions.IllegalBooleanArgumentException;
import de.hskempten.tabulang.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class IfElseNode extends TernaryStatementNode {
    public IfElseNode(Node leftNode, Node middleNode, Node rightNode, TextPosition textPosition) {
        super(leftNode, middleNode, rightNode, textPosition);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object left = getLeftNode().evaluateNode(interpretation);
        if (!(left instanceof InternalBoolean leftBool)) {
            throw new IllegalBooleanArgumentException(toString());
        }
        if (leftBool.getBoolean()) {
            return getMiddleNode().evaluateNode(interpretation);
        } else {
            return getRightNode().evaluateNode(interpretation);
        }
    }

    @Override
    public String toString() {
        return "if(" + getLeftNode() + ") " + getMiddleNode() + " else " + getRightNode();
    }
}
