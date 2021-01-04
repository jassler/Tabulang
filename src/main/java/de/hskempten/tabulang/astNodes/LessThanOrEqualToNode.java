package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.exceptions.IllegalBooleanOperandArgumentException;
import de.hskempten.tabulang.datatypes.exceptions.IllegalOperandArgumentException;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.math.BigDecimal;

public class LessThanOrEqualToNode extends BinaryPredicateNode {
    public LessThanOrEqualToNode(TermNode leftNode, TermNode rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object left = getLeftNode().evaluateNode(interpretation);
        Object right = getRightNode().evaluateNode(interpretation);
        if(left instanceof InternalNumber l && right instanceof InternalNumber r){
            return l.compareTo(r) <= 0;
        } else {
            throw new IllegalBooleanOperandArgumentException(toString());
        }
    }

    @Override
    public String toString() {
        return getLeftNode() + " <= " + getRightNode();
    }
}
