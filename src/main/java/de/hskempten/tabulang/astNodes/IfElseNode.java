package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.datatypes.exceptions.IllegalBooleanOperandArgumentException;
import de.hskempten.tabulang.interpretTest.Interpretation;

public class IfElseNode extends TernaryStatementNode {
    public IfElseNode(Node leftNode, Node middleNode, Node rightNode) {
        super(leftNode, middleNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object left = getLeftNode().evaluateNode(interpretation);
        if (!(left instanceof InternalBoolean leftBool)) {
            throw new IllegalBooleanOperandArgumentException(toString());
        }
        if (leftBool.getaBoolean()) {
            return getMiddleNode().evaluateNode(interpretation);
        } else {
            return getRightNode().evaluateNode(interpretation);
        }
    }

    @Override
    public String toString() {
        return "if(" +  getLeftNode() + ") " + getMiddleNode() + " else " + getRightNode();
    }
}
