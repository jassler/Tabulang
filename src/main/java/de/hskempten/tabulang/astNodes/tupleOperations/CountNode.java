package de.hskempten.tabulang.astNodes.tupleOperations;

import de.hskempten.tabulang.astNodes.term.TermNode;
import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.TupleOperation;
import de.hskempten.tabulang.interpreter.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

import java.math.BigInteger;

public class CountNode extends TermNode {
    private TermNode node;

    public CountNode(TermNode node, TextPosition textPosition) {
        super(textPosition);
        this.node = node;
    }

    public TermNode getNode() {
        return node;
    }

    public void setNode(TermNode node) {
        this.node = node;
    }

    /**
     * Counts the size of a specified object.
     * If the object is a Tuple, it counts its size.
     * Otherwise it returns 1.
     *
     * @return the size of the specified object.
     */
    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object o = node.evaluateNode(interpretation);
        if (o instanceof TupleOperation<?> tuple) {
            return new InternalNumber(new BigInteger(Integer.toString(tuple.getWidth())), new BigInteger("1"));
        } else {
            return new InternalNumber(new BigInteger("1"), new BigInteger("1"));
        }
    }
}
