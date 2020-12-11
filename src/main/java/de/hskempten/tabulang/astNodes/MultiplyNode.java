package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.interpretTest.Interpretation;

import java.math.BigDecimal;

public class MultiplyNode extends BinaryArithmeticNode{
    public MultiplyNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        return ((BigDecimal) getLeftNode().evaluateNode(interpretation)).multiply((BigDecimal) getRightNode().evaluateNode(interpretation));
    }
}
