package de.hskempten.tabulang.astNodes.predicate;


import de.hskempten.tabulang.astNodes.term.TermNode;
import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class NotEqualNode extends BinaryPredicateNode {
    public NotEqualNode(TermNode leftNode, TermNode rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        InternalNumber leftNumber = verifyAndReturnNumber(getLeftNode(), interpretation);
        InternalNumber rightNumber = verifyAndReturnNumber(getRightNode(), interpretation);
        return new InternalBoolean(leftNumber.compareTo(rightNumber) != 0);
    }
}
