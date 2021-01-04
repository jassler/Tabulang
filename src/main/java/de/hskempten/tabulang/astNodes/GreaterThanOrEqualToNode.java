package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.exceptions.IllegalOperandArgumentException;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.math.BigDecimal;

public class GreaterThanOrEqualToNode extends BinaryPredicateNode {
    public GreaterThanOrEqualToNode(TermNode leftNode, TermNode rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object left = getLeftNode().evaluateNode(interpretation);
        Object right = getRightNode().evaluateNode(interpretation);
        if(left instanceof InternalNumber l && right instanceof InternalNumber r){
            return new InternalBoolean(l.compareTo(r) >= 0);
        } else {
            throw new IllegalOperandArgumentException("Operation '" + left + " (" + left.getClass() + ") >= " + right + " (" + right.getClass() + ") can not be executed. " +
                    "Allowed operands: Boolean.");
        }
        }
}
