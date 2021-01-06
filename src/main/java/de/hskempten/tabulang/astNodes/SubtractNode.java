package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.InternalString;
import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.datatypes.exceptions.IllegalOperandArgumentException;
import de.hskempten.tabulang.datatypes.exceptions.TupleCannotBeTransformedException;
import de.hskempten.tabulang.datatypes.exceptions.TypeMismatchException;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class SubtractNode extends BinaryArithmeticNode{
    public SubtractNode(TermNode leftNode, TermNode rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object left = getLeftNode().evaluateNode(interpretation);
        left = ifTupleTransform(left);
        if (!(left instanceof Table) && !(left instanceof InternalNumber)) {
            throw new IllegalOperandArgumentException(getTextPosition(), getLeftNode().getTextPosition());
        }
        Object right = getRightNode().evaluateNode(interpretation);
        right = ifTupleTransform(right);
        if(!(right instanceof Table) && !(right instanceof InternalNumber)){
            throw new IllegalOperandArgumentException(getTextPosition(), getRightNode().getTextPosition());
        }

        if (left instanceof InternalNumber leftNumber && right instanceof InternalNumber rightNumber) {
            return leftNumber.subtract(rightNumber);
        }
        if (left instanceof Table leftTable && right instanceof Table rightTable) {
            return leftTable.difference(rightTable);
        } else {
            throw new TypeMismatchException(getTextPosition());
        }

    }

    @Override
    public String toString() {
        return getLeftNode() + " - " + getRightNode();
    }
}
