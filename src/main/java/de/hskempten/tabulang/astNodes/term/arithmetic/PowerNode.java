package de.hskempten.tabulang.astNodes.term.arithmetic;


import de.hskempten.tabulang.astNodes.term.TermNode;
import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class PowerNode extends BinaryArithmeticNode {
    public PowerNode(TermNode leftNode, TermNode rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        InternalNumber leftNumber = verifyAndReturnNumber(getLeftNode(), interpretation);
        InternalNumber rightNumber = verifyAndReturnNumber(getRightNode(), interpretation);
        return leftNumber.pow(rightNumber);
    }

    @Override
    public String toString() {
        return getLeftNode() + " ^ " + getRightNode();
    }
}