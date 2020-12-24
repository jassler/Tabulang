package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Identifier;
import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.math.BigInteger;

public class CountHorizontalNode extends TermNode{
    private TermNode node;

    public CountHorizontalNode(TermNode node) {
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
        if(!(o instanceof Table)){
            if(o instanceof Tuple){
                return  new InternalNumber(new BigInteger(Integer.toString(((Tuple) o).size())), new BigInteger("1"));
            } else {
                return new InternalNumber(new BigInteger("1"), new BigInteger("1"));
            }
        } else {
            if(((Table<?>) o).getRows().size() > 0){
                return  new InternalNumber(new BigInteger(Integer.toString(((Table<?>) o).getRows().get(0).size())), new BigInteger("1"));
            } else {
                return new InternalNumber(new BigInteger("0"), new BigInteger("1"));
            }
        }
    }
}
