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
        if (left instanceof InternalNumber && right instanceof InternalNumber) {
            //TODO
            return "Mod Operation not yet implemented";
            //return getNumericValue(getLeftNode(), interpretation).remainder(getNumericValue(getRightNode(), interpretation));
        } else {
            throw new IllegalOperandArgumentException("Operation '" + left + " (" + left.getClass() + ") mod " + right + " (" + right.getClass() + ") can not be executed. " +
                    "Allowed operands: Numbers.");
        }
    }
}
