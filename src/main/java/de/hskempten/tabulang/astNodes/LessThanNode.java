package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.interpretTest.Interpretation;

import java.math.BigDecimal;

public class LessThanNode extends BinaryPredicateNode {
    public LessThanNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        BigDecimal valueLeft = getNumericValue(getLeftNode(), interpretation);
        BigDecimal valueRight = getNumericValue(getRightNode(), interpretation);
        return valueLeft.compareTo(valueRight) == -1;    }

    @Override
    public String toString() {
        return "LessThanNode{}";
    }
}
