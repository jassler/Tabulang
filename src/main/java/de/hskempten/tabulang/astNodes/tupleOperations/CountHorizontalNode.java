package de.hskempten.tabulang.astNodes.tupleOperations;

import de.hskempten.tabulang.astNodes.term.TermNode;
import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.interpreter.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

import java.math.BigInteger;

public class CountHorizontalNode extends TermNode {
    private TermNode node;

    public CountHorizontalNode(TermNode node, TextPosition textPosition) {
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
     * Counts the horizontal size of a specified object.
     * If the object is a Table, it counts the number of columns.
     * If the object is a Tuple, it counts its size.
     * Otherwise it returns 1.
     *
     * @return the horizontal size of the specified object.
     */
    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object o = node.evaluateNode(interpretation);
        int width;

        if (o instanceof Table<?> table) {
            width = table.getNumberOfColumns();
        }

        else if (o instanceof Tuple<?> tuple) {
            width = tuple.size();
        }
        else {
            width = 1;
        }
        return new InternalNumber(new BigInteger(Integer.toString(width)), new BigInteger("1"));
    }
}
