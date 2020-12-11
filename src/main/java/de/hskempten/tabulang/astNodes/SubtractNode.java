package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.math.BigDecimal;

public class SubtractNode extends BinaryArithmeticNode{
    public SubtractNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object left = getLeftNode().evaluateNode(interpretation);
        Object right = getRightNode().evaluateNode(interpretation);
        if(left instanceof BigDecimal && right instanceof BigDecimal){
            BigDecimal l = (BigDecimal) left;
            BigDecimal r = (BigDecimal) right;
            return l.subtract(r);
        } else {
            return ((Table) left).difference((Table) right);
        }
    }
}
