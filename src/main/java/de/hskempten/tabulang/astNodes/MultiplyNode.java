package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.interpretTest.Interpretation;

import java.math.BigDecimal;

public class MultiplyNode extends BinaryArithmeticNode{
    public MultiplyNode(TermNode leftNode, TermNode rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        return getNumericValue(getLeftNode(), interpretation).multiply(getNumericValue(getRightNode(), interpretation));
    }

    @Override
    public String toString() {
        return "MultiplyNode{} " + super.toString();
    }
}
