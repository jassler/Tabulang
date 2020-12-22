package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.exceptions.TypeMismatchException;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.math.BigDecimal;

public class SubtractNode extends BinaryArithmeticNode{
    public SubtractNode(TermNode leftNode, TermNode rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object left = getTableOrNumericValue(getLeftNode(), interpretation);
        Object right = getTableOrNumericValue(getRightNode(), interpretation);
        if(left instanceof InternalNumber && right instanceof InternalNumber){
            return ((InternalNumber) left).subtract((InternalNumber) right);
        } else if(left instanceof Table && right instanceof Table){
            return ((Table) left).difference((Table) right);
        } else {
            throw new TypeMismatchException(left.getClass().getSimpleName(), right.getClass().getSimpleName());
        }
    }
}
