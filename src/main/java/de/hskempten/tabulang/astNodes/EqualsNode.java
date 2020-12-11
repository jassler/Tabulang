package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.interpretTest.Interpretation;

import java.math.BigDecimal;

public class EqualsNode extends BinaryPredicateNode {
    public EqualsNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Boolean evaluateNode(Interpretation interpretation) {
        BigDecimal valueLeft = getNumericValue(getLeftNode(), interpretation);
        BigDecimal valueRight = getNumericValue(getRightNode(), interpretation);
        return valueLeft.compareTo(valueRight) == 0;
    }

    @Override
    public String toString() {
        return "EqualsNode{} " + super.toString();
    }
}
