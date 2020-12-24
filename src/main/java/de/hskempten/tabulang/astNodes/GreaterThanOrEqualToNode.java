package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.interpretTest.Interpretation;

import java.math.BigDecimal;

public class GreaterThanOrEqualToNode extends BinaryPredicateNode {
    public GreaterThanOrEqualToNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        //TODO remove placeholder return once InternalNumber has compareMethod
        return getNumericValue(getLeftNode(), interpretation).getFloatValue() >= getNumericValue(getRightNode(), interpretation).getFloatValue();
        //return valueLeft.compareTo(valueRight) >= 0;
        }
}
