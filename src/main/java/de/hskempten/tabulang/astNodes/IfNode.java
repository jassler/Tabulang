package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.datatypes.exceptions.IllegalBooleanOperandArgumentException;
import de.hskempten.tabulang.datatypes.exceptions.IllegalOperandArgumentException;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.util.HashMap;

public class IfNode extends BinaryStatementNode{
    public IfNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object left = getLeftNode().evaluateNode(interpretation);
        if (!(left instanceof InternalBoolean bool)) {
            throw new IllegalBooleanOperandArgumentException(toString());
        }
        if(bool.getaBoolean()) {
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
