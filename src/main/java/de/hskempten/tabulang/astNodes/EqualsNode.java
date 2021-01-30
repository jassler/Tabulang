package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.InternalString;
import de.hskempten.tabulang.datatypes.exceptions.IllegalBooleanOperandArgumentException;
import de.hskempten.tabulang.datatypes.exceptions.IllegalNumberOperandArgumentException;
import de.hskempten.tabulang.datatypes.exceptions.IllegalOperandArgumentException;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class EqualsNode extends BinaryPredicateNode {
    public EqualsNode(TermNode leftNode, TermNode rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object left = getLeftNode().evaluateNode(interpretation);
        Object right = getRightNode().evaluateNode(interpretation);

        if(left instanceof InternalString leftString && right instanceof InternalString rightString) {
            return new InternalBoolean(leftString.equals(rightString));
        }
        if (!(left instanceof InternalNumber leftNumber)) {
            throw new IllegalOperandArgumentException(getTextPosition(), left.getClass().getSimpleName(), getLeftNode().getTextPosition().getContent(), "Allowed operands: Number or String.");
        }
        if (!(right instanceof InternalNumber rightNumber)) {
            throw new IllegalOperandArgumentException(getTextPosition(), right.getClass().getSimpleName(), getRightNode().getTextPosition().getContent(), "Allowed operands: Number or String.");
        }
        return new InternalBoolean(leftNumber.compareTo(rightNumber) == 0);
    }

    @Override
    public String toString() {
        return getLeftNode() + " == " + getRightNode();
    }
}
