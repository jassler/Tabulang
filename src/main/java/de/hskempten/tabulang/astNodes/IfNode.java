package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.InternalBoolean;
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
            throw new IllegalOperandArgumentException("Operation 'if(" + left + " (" + left.getClass() + ")) can not be executed. " +
                    "Allowed operands: Boolean.");
        }
        if(bool.getaBoolean()) {
            return getRightNode().evaluateNode(interpretation);
        } else {
            return null;
        }
    }
}
