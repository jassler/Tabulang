package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.exceptions.IllegalNumberOperandArgumentException;
import de.hskempten.tabulang.tokenizer.TextPosition;

public abstract class BinaryArithmeticNode extends ArithmeticNode {
    private TermNode leftNode;
    private TermNode rightNode;

    public BinaryArithmeticNode(TermNode leftNode, TermNode rightNod, TextPosition textPosition) {
        super(textPosition);
        this.leftNode = leftNode;
        this.rightNode = rightNod;
    }

    public TermNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(TermNode leftNode) {
        this.leftNode = leftNode;
    }

    public TermNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(TermNode rightNode) {
        this.rightNode = rightNode;
    }

    public void throwExceptionIfNotNumbers(Object left, Object right){
        if (!(left instanceof InternalNumber)) {
            throw new IllegalNumberOperandArgumentException(getTextPosition(), left.getClass().getSimpleName(), getLeftNode().getTextPosition().getContent());
        }
        if (!(right instanceof InternalNumber)) {
            throw new IllegalNumberOperandArgumentException(getTextPosition(), right.getClass().getSimpleName(), getRightNode().getTextPosition().getContent());
        }
    }

    @Override
    public String toString() {
        return "BinaryArithmeticNode{" +
                "leftNode=" + leftNode +
                ", rightNode=" + rightNode +
                "} ";
    }
}
