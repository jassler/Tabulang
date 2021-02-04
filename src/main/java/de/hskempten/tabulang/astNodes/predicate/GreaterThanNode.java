package de.hskempten.tabulang.astNodes.predicate;


import de.hskempten.tabulang.interpreter.Interpretation;
import de.hskempten.tabulang.astNodes.term.TermNode;
import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class GreaterThanNode extends BinaryPredicateNode {
    public GreaterThanNode(TermNode leftNode, TermNode rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    /**
     * Compares two evaluated nodes to decide if left node value is greater than right node value.
     *
     * @return InternalBoolean with value true if value of left node is greater than value of right node,
     * InternalBoolean with value false otherwise.
     */
    @Override
    public Object evaluateNode(Interpretation interpretation) {
        InternalNumber leftNumber = getLeftNode().verifyAndReturnNumber(interpretation);
        InternalNumber rightNumber = getRightNode().verifyAndReturnNumber(interpretation);
        return new InternalBoolean(leftNumber.compareTo(rightNumber) == 1);
    }

    @Override
    public String toString() {
        return getLeftNode() + " > " + getRightNode();
    }
}
