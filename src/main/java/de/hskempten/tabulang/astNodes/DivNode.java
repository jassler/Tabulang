package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.interpretTest.Interpretation;

import java.math.BigDecimal;

public class DivNode extends BinaryArithmeticNode{
    public DivNode(TermNode leftNode, TermNode rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        return "Div Operation not yet implemented";
        //return getNumericValue(getLeftNode(), interpretation).divideToIntegralValue(getNumericValue(getRightNode(), interpretation));
    }
}
