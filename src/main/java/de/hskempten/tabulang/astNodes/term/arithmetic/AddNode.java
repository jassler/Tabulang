package de.hskempten.tabulang.astNodes.term.arithmetic;


import de.hskempten.tabulang.astNodes.term.TermNode;
import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.InternalString;
import de.hskempten.tabulang.datatypes.exceptions.InterpreterException;
import de.hskempten.tabulang.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class AddNode extends BinaryArithmeticNode {
    public AddNode(TermNode leftNode, TermNode rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object left = getLeftNode().evaluateNode(interpretation);
        if (!(left instanceof InternalString) && !(left instanceof InternalNumber)) {
            throw new InterpreterException(getTextPosition(), left.getClass().getSimpleName(), getLeftNode().getTextPosition().getContent(), "Allowed operands: Number and/or String.");
        }
        Object right = getRightNode().evaluateNode(interpretation);
        if (!(right instanceof InternalString) && !(right instanceof InternalNumber)) {
            throw new InterpreterException(getTextPosition(), right.getClass().getSimpleName(), getRightNode().getTextPosition().getContent(), "Allowed operands: Number and/or String.");
        }
        if ((left instanceof InternalNumber leftNumber) && (right instanceof InternalNumber rightNumber)) {
            return leftNumber.add(rightNumber);
        } else {
            return new InternalString(left.toString() + right.toString());
        }
    }

    @Override
    public String toString() {
        return getLeftNode() + " + " + getRightNode();
    }
}
