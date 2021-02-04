package de.hskempten.tabulang.astNodes.predicate;


import de.hskempten.tabulang.interpreter.Interpretation;
import de.hskempten.tabulang.astNodes.term.TermNode;
import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class LessThanOrEqualToNode extends BinaryPredicateNode {
    public LessThanOrEqualToNode(TermNode leftNode, TermNode rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    /**
     * Compares two evaluated nodes to decide if left node value is less than or equal to right node value.
     *
     * @return InternalBoolean with value true if value of left node is less than or equal to value of right node,
     * InternalBoolean with value false otherwise.
     */
    @Override
    public Object evaluateNode(Interpretation interpretation) {
        InternalNumber leftNumber = getLeftNode().verifyAndReturnNumber(interpretation);
        InternalNumber rightNumber = getRightNode().verifyAndReturnNumber(interpretation);
        return new InternalBoolean(leftNumber.compareTo(rightNumber) <= 0);
    }

    @Override
    public String toString() {
        return getLeftNode() + " <= " + getRightNode();
    }
}
