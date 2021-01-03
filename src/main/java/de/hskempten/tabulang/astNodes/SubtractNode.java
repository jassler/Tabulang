package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.datatypes.exceptions.IllegalOperandArgumentException;
import de.hskempten.tabulang.datatypes.exceptions.TupleCannotBeTransformedException;
import de.hskempten.tabulang.interpretTest.Interpretation;

public class SubtractNode extends BinaryArithmeticNode{
    public SubtractNode(TermNode leftNode, TermNode rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object left = getLeftNode().evaluateNode(interpretation);
        Object right = getRightNode().evaluateNode(interpretation);
        try {
            left = ifTupleTransform(left);
            right = ifTupleTransform(right);
        } catch (TupleCannotBeTransformedException transformedException){
            throw new IllegalOperandArgumentException("Operation '" + left + " (" + left.getClass() + ") - " + right + " (" + right.getClass() + ")' can not be executed. " +
                    "Allowed operands: Numbers or Tables.");
        }
        if (left instanceof InternalNumber && right instanceof InternalNumber) {
            return ((InternalNumber) left).subtract((InternalNumber) right);
        } else if (left instanceof Table && right instanceof Table) {
            return ((Table) left).difference((Table) right);
        } else {
            throw new IllegalOperandArgumentException("Operation '" + left + " (" + left.getClass() + ") - " + right + " (" + right.getClass() + ")' can not be executed. " +
                    "Allowed operands: Numbers or Tables.");
        }
    }
}
