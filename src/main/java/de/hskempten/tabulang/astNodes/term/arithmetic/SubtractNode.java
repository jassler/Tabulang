package de.hskempten.tabulang.astNodes.term.arithmetic;


import de.hskempten.tabulang.interpreter.Interpretation;
import de.hskempten.tabulang.astNodes.term.TermNode;
import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.exceptions.InterpreterException;
import de.hskempten.tabulang.datatypes.exceptions.TypeMismatchException;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class SubtractNode extends BinaryArithmeticNode {
    public SubtractNode(TermNode leftNode, TermNode rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    /**
     * Subtracts the value of the first evaluated node by the value of the second evaluated node.
     * If both values are Numbers, it will calculate the difference between both.
     * If both values are Tables, it will create a new Table with rows that are in the first value, but not in the second.
     *
     * @return the difference of both values or a Table with rows that are exclusive to the first value.
     */
    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object left = getLeftNode().ifTupleTransform(interpretation);
        if (!(left instanceof Table) && !(left instanceof InternalNumber)) {
            throw new InterpreterException(getTextPosition(), left.getClass().getSimpleName(), getLeftNode().getTextPosition().getContent(), "Allowed operands: Number or Table.");
        }
        Object right = getRightNode().ifTupleTransform(interpretation);
        if (!(right instanceof Table) && !(right instanceof InternalNumber)) {
            throw new InterpreterException(getTextPosition(), right.getClass().getSimpleName(), getRightNode().getTextPosition().getContent(), "Allowed operands: Number or Table.");
        }

        if (left instanceof InternalNumber leftNumber && right instanceof InternalNumber rightNumber) {
            return leftNumber.subtract(rightNumber);
        }
        if (left instanceof Table<?> leftTable && right instanceof Table rightTable) {
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
