package de.hskempten.tabulang.astNodes.tupleOperations;

import de.hskempten.tabulang.astNodes.term.TermNode;
import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

import java.math.BigInteger;

public class CountVerticalNode extends TermNode {
    private TermNode node;

    public CountVerticalNode(TermNode node, TextPosition textPosition) {
        super(textPosition);
        this.node = node;
    }

    public TermNode getNode() {
        return node;
    }

    public void setNode(TermNode node) {
        this.node = node;
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object o = node.evaluateNode(interpretation);
        if (o instanceof Table<?> table) {
            return new InternalNumber(new BigInteger(Integer.toString(table.getNumberOfRows())), new BigInteger("1"));
        } else {
            return new InternalNumber(new BigInteger("1"), new BigInteger("1"));
        }
    }
}
