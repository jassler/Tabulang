package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.interpretTest.Interpretation;

import java.math.BigDecimal;

public class ModNode extends BinaryArithmeticNode{
    public ModNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        return ((BigDecimal) getLeftNode().evaluateNode(interpretation)).remainder((BigDecimal) getRightNode().evaluateNode(interpretation));
    }
}
