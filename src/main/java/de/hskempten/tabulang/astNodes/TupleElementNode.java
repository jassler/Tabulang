package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.datatypes.InternalString;
import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.datatypes.exceptions.TupleNameNotFoundException;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class TupleElementNode extends BinaryTermNode{


    public TupleElementNode(TermNode leftNode, TermNode rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object tuple = getLeftNode().evaluateNode(interpretation);
        if(tuple instanceof Tuple tuple1) {
            Tuple t = (Tuple) tuple1;
        }
        if(tuple instanceof Table table1){
            Table tab = (Table) table1;
        }
        if(tuple instanceof Tuple){
            Object columnIdentifier = getRightNode().evaluateNode(interpretation);
            if(columnIdentifier instanceof InternalString){
                if(((Tuple<?>) tuple).getNames().getNames().contains(((InternalString) columnIdentifier).getString())){
                    return ((Tuple) tuple).get(((InternalString) columnIdentifier).getString());
                } else {
                    throw new TupleNameNotFoundException(((InternalString) columnIdentifier).getString());
                }
            } else {
                throw new IllegalArgumentException("Expected String but got: " + columnIdentifier.getClass().getSimpleName());
            }
        } else if (tuple instanceof Table){
            Object columnIdentifier = getRightNode().evaluateNode(interpretation);
            if(columnIdentifier instanceof InternalString){
                if(((Table<?>) tuple).getColNames().contains(((InternalString) columnIdentifier).getString())){
                    int index = ((Table<?>) tuple).getColNames().getIndexOf(((InternalString) columnIdentifier).getString());
                    return ((Table<?>) tuple).getRow(index);
                } else {
                    throw new TupleNameNotFoundException(((InternalString) columnIdentifier).getString());
                }
            } else {
                throw new IllegalArgumentException("Expected String but got: " + columnIdentifier.getClass().getSimpleName());
            }
        } else {
            throw new IllegalArgumentException("Expected Tuple but got: " + tuple.getClass().getSimpleName());
        }
    }

    @Override
    public String toString() {
        return getLeftNode() + "." + getRightNode();
    }
}
