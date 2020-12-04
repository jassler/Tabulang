package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.interpretTest.Interpretation;

import java.math.BigDecimal;

public class AddNode extends ArithmeticNode{
    public AddNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object left = getLeftNode().evaluateNode(interpretation);
        Object right = getRightNode().evaluateNode(interpretation);
        if(left instanceof String || right instanceof String){
            return left.toString() + right.toString();
        } else {
            BigDecimal l = (BigDecimal) left;
            BigDecimal r = (BigDecimal) right;
            return l.add(r);
        }
    }

    @Override
    public String toString() {
        return "AddNode{} " + super.toString();
    }
}
