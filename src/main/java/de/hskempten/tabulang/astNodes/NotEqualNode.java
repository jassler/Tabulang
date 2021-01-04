package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.exceptions.IllegalNumberOperandArgumentException;
import de.hskempten.tabulang.datatypes.exceptions.IllegalOperandArgumentException;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.math.BigDecimal;

public class NotEqualNode extends BinaryPredicateNode {
    public NotEqualNode(TermNode leftNode, TermNode rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        try {
            Object left = getLeftNode().evaluateNode(interpretation);
            Object right = getRightNode().evaluateNode(interpretation);
            if (!(left instanceof InternalNumber leftNumber) || !(right instanceof InternalNumber rightNumber)) {
                throw new IllegalNumberOperandArgumentException(toString());
            }
            return leftNumber.compareTo(rightNumber) != 0;
        } catch (IllegalNumberOperandArgumentException numberOperandArgumentException){
            interpretation.exitProgram(numberOperandArgumentException);
        }
        return null;
    }
}
