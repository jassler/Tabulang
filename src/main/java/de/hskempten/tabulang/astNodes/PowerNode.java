package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.interpretTest.Interpretation;

import java.math.BigDecimal;

public class PowerNode extends BinaryArithmeticNode{
    public PowerNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        return ((BigDecimal) getLeftNode().evaluateNode(interpretation)).pow(((BigDecimal) getRightNode().evaluateNode(interpretation)).intValue());
    }
}
