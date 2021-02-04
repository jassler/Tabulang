package de.hskempten.tabulang.astNodes.predicate;

import de.hskempten.tabulang.interpreter.Interpretation;
import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class OrNode extends BinaryPredicateNode {
    public OrNode(PredicateNode leftNode, PredicateNode rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    /**
     * Compares two evaluated nodes with OR operator.
     *
     * @return InternalBoolean with value of OR operation on evaluated left node and evaluated right node.
     */
    @Override
    public Object evaluateNode(Interpretation interpretation) {
        InternalBoolean leftBool = getLeftNode().verifyAndReturnBoolean(interpretation);
        InternalBoolean rightBool = getRightNode().verifyAndReturnBoolean(interpretation);
        return new InternalBoolean(leftBool.getBoolean() || rightBool.getBoolean());
    }

    @Override
    public String toString() {
        return getLeftNode() + " || " + getRightNode();
    }
}
