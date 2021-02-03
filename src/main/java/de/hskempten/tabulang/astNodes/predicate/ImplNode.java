package de.hskempten.tabulang.astNodes.predicate;

import de.hskempten.tabulang.interpreter.Interpretation;
import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class ImplNode extends BinaryPredicateNode {
    public ImplNode(PredicateNode leftNode, PredicateNode rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    /**
     * Compares two evaluated nodes using implication.
     *
     * @return InternalBoolean with value false if left value is false and right value is true,
     * InternalBoolean with value true otherwise.
     */
    @Override
    public Object evaluateNode(Interpretation interpretation) {
        InternalBoolean leftBool = getLeftNode().verifyAndReturnBoolean(interpretation);
        InternalBoolean rightBool = getRightNode().verifyAndReturnBoolean(interpretation);
        return new InternalBoolean(leftBool.getBoolean() || !rightBool.getBoolean());
    }

    @Override
    public String toString() {
        return getLeftNode() + " impl " + getRightNode();
    }
}
