package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.exceptions.IllegalOperandArgumentException;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.math.BigDecimal;

public class ModNode extends BinaryArithmeticNode{
    public ModNode(TermNode leftNode, TermNode rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object left = getLeftNode().evaluateNode(interpretation);
        Object right = getRightNode().evaluateNode(interpretation);
        if (!(left instanceof InternalNumber leftValue) || !(right instanceof InternalNumber rightValue)) {
            throw new IllegalOperandArgumentException("Operation '" + left + " (" + left.getClass() + ") mod " + right + " (" + right.getClass() + ") can not be executed. " +
                    "Allowed operands: Numbers.");
        }
        return leftValue.mod(rightValue);
    }
}
