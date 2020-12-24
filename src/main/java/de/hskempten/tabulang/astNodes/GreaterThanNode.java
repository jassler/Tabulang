package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.math.BigDecimal;

public class GreaterThanNode extends BinaryPredicateNode {
    public GreaterThanNode(TermNode leftNode, TermNode rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        //TODO remove placeholder return once InternalNumber has compareMethod
        return getNumericValue(getLeftNode(), interpretation).getFloatValue() > getNumericValue(getRightNode(), interpretation).getFloatValue();
        //return valueLeft.compareTo(valueRight) == 1;
    }

    @Override
    public String toString() {
        return "GreaterThanNode{} " + super.toString();
    }
}
