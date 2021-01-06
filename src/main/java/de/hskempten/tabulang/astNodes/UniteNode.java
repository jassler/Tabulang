package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.exceptions.IllegalTableOperandArgumentException;
import de.hskempten.tabulang.datatypes.exceptions.TupleCannotBeTransformedException;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class UniteNode extends BinaryTermNode{
    public UniteNode(Node leftNode, Node rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object left = getLeftNode().evaluateNode(interpretation);
        Object right = getRightNode().evaluateNode(interpretation);
        try {
            left = checkIfTable(left);
            right = checkIfTable(right);
        } catch (TupleCannotBeTransformedException | IllegalArgumentException runtimeException){
            throw new IllegalTableOperandArgumentException(toString());
        }
        return ((Table) left).union((Table) right);
    }

    @Override
    public String toString() {
        return getLeftNode() + " unite " + getRightNode();
    }
}
