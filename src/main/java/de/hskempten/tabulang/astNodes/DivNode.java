package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.interpretTest.Interpretation;

import java.math.BigDecimal;

public class DivNode extends BinaryArithmeticNode{
    public DivNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        return ((BigDecimal) getLeftNode().evaluateNode(interpretation)).divideToIntegralValue((BigDecimal) getRightNode().evaluateNode(interpretation));
    }
}
