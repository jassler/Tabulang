package de.hskempten.tabulang.astNodes.predicate;

import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.interpreter.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class XorNode extends BinaryPredicateNode {
    public XorNode(PredicateNode leftNode, PredicateNode rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    /**
     * Compares two evaluated nodes with XOR operator.
     *
     * @return InternalBoolean with value of XOR operation on evaluated left node and evaluated right node.
     */
    @Override
    public Object evaluateNode(Interpretation interpretation) {
        InternalBoolean leftBool = getLeftNode().verifyAndReturnBoolean(interpretation);
        InternalBoolean rightBool = getRightNode().verifyAndReturnBoolean(interpretation);
        return new InternalBoolean(leftBool.getBoolean() ^ rightBool.getBoolean());
    }

    @Override
    public String toString() {
        return getLeftNode() + " ^ " + getRightNode();
    }
}
