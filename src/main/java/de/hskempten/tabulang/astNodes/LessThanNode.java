package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.exceptions.IllegalBooleanOperandArgumentException;
import de.hskempten.tabulang.datatypes.exceptions.IllegalOperandArgumentException;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.math.BigDecimal;

public class LessThanNode extends BinaryPredicateNode {
    public LessThanNode(TermNode leftNode, TermNode rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object left = getLeftNode().evaluateNode(interpretation);
        Object right = getRightNode().evaluateNode(interpretation);
        if(left instanceof InternalNumber leftNumber && right instanceof InternalNumber rightNumber){
            return new InternalBoolean(leftNumber.compareTo(rightNumber) == -1);
        } else {
            throw new IllegalBooleanOperandArgumentException(toString());
        }
    }

    @Override
    public String toString() {
        return getLeftNode() + " < " + getRightNode();
    }
}
