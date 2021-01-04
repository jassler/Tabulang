package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.exceptions.IllegalNumberOperandArgumentException;
import de.hskempten.tabulang.datatypes.exceptions.IllegalOperandArgumentException;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.math.BigDecimal;

public class MultiplyNode extends BinaryArithmeticNode{
    public MultiplyNode(TermNode leftNode, TermNode rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object left = getLeftNode().evaluateNode(interpretation);
        Object right = getRightNode().evaluateNode(interpretation);
        try {
            if (!(left instanceof InternalNumber leftNumber) || !(right instanceof InternalNumber rightNumber)) {
                throw new IllegalNumberOperandArgumentException(toString());
            }
            return leftNumber.multiply(rightNumber);
        } catch (IllegalNumberOperandArgumentException e){
            interpretation.exitProgram(e);
        }
        return null;
    }

    @Override
    public String toString() {
        return getLeftNode() + " * " + getRightNode();
    }
}
