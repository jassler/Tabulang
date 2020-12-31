package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.exceptions.IllegalOperandArgumentException;
import de.hskempten.tabulang.interpretTest.Interpretation;

public class OrNode extends BinaryPredicateNode{
    public OrNode(PredicateNode leftNode, PredicateNode rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object left = getLeftNode().evaluateNode(interpretation);
        Object right = getRightNode().evaluateNode(interpretation);
        if (left instanceof Boolean && right instanceof Boolean) {
            return ((Boolean)left) || ((Boolean)right);
        } else {
            throw new IllegalOperandArgumentException("Operation '" + left + " (" + left.getClass() + ") || " + right + " (" + right.getClass() + ") can not be executed. " +
                    "Allowed operands: Boolean.");
        }
    }
}
