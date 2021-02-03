package de.hskempten.tabulang.astNodes.term.arithmetic;


import de.hskempten.tabulang.astNodes.term.TermNode;
import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.exceptions.IllegalNumberArgumentException;
import de.hskempten.tabulang.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class ModNode extends BinaryArithmeticNode {
    public ModNode(TermNode leftNode, TermNode rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object left = getLeftNode().evaluateNode(interpretation);
        Object right = getRightNode().evaluateNode(interpretation);
        if (!(left instanceof InternalNumber leftNumber) || !(right instanceof InternalNumber rightNumber)) {
            throw new IllegalNumberArgumentException(getTextPosition(), getLeftNode().getTextPosition(), left.getClass().getSimpleName(), getRightNode().getTextPosition(), right.getClass().getSimpleName());
        }
        return leftNumber.mod(rightNumber);
    }

    @Override
    public String toString() {
        return getLeftNode() + " mod " + getRightNode();
    }
}
