package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class LessThanNode extends BinaryPredicateNode {
    public LessThanNode(TermNode leftNode, TermNode rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        InternalNumber leftNumber = verifyAndReturnNumber(getLeftNode(), interpretation);
        InternalNumber rightNumber = verifyAndReturnNumber(getRightNode(), interpretation);
        return new InternalBoolean((leftNumber).compareTo(rightNumber) == -1);
    }

    @Override
    public String toString() {
        return getLeftNode() + " < " + getRightNode();
    }
}
