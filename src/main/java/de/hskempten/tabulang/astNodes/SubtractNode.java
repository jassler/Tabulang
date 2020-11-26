package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.interpretTest.Interpretation;

import java.math.BigDecimal;

public class SubtractNode extends ArithmeticNode{
    public SubtractNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation i) {
        Object left = getLeftNode().evaluateNode(i);
        Object right = getRightNode().evaluateNode(i);
        if(left instanceof BigDecimal && right instanceof BigDecimal){
            BigDecimal l = (BigDecimal) left;
            BigDecimal r = (BigDecimal) right;
            return l.subtract(r);
        } else {
            //TODO Tabellen
            return null;
        }
    }
}
