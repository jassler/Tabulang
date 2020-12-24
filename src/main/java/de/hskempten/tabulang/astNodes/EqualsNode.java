package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.math.BigDecimal;

public class EqualsNode extends BinaryPredicateNode {
    public EqualsNode(TermNode leftNode, TermNode rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Boolean evaluateNode(Interpretation interpretation) {
        //TODO remove placeholder return once InternalNumber has compareMethod
        return getNumericValue(getLeftNode(), interpretation).getFloatValue() == getNumericValue(getRightNode(), interpretation).getFloatValue();
        //return valueLeft.compareTo(valueRight) == 0;
    }

    @Override
    public String toString() {
        return "EqualsNode{} " + super.toString();
    }
}
