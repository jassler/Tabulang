package de.hskempten.tabulang.astNodes.term.arithmetic;


import de.hskempten.tabulang.interpreter.Interpretation;
import de.hskempten.tabulang.astNodes.term.TermNode;
import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class DivNode extends BinaryArithmeticNode {
    public DivNode(TermNode leftNode, TermNode rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    /**
     * Divides the value of the first evaluated node by the value of the second evaluated node and returns the remainder rounded.
     *
     * @return the remainder of the division of both values rounded to an integral value.
     */
    @Override
    public Object evaluateNode(Interpretation interpretation) {
        InternalNumber leftNumber = getLeftNode().verifyAndReturnNumber(interpretation);
        InternalNumber rightNumber = getRightNode().verifyAndReturnNumber(interpretation);
        return leftNumber.div(rightNumber);
    }

    @Override
    public String toString() {
        return getLeftNode() + " div " + getRightNode();
    }
}

