package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.math.BigInteger;

public class CountVerticalNode extends TermNode{
    private TermNode node;

    public CountVerticalNode(TermNode node) {
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
        if(o instanceof Table){
            return new InternalNumber(new BigInteger(Integer.toString(((Table<?>) o).getRows().size())), new BigInteger("1"));
        } else {
            return new InternalNumber(new BigInteger("1"), new BigInteger("1"));
        }
    }
}
