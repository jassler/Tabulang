package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.datatypes.Identifier;
import de.hskempten.tabulang.datatypes.InternalString;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.datatypes.exceptions.TupleNameNotFoundException;
import de.hskempten.tabulang.interpretTest.Interpretation;

public class TupleElementNode extends BinaryTermNode{


    public TupleElementNode(TermNode leftNode, TermNode rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object tuple = getLeftNode().evaluateNode(interpretation);
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
        } else {
            throw new IllegalArgumentException("Expected Tuple but got: " + tuple.getClass().getSimpleName());
        }
    }

    @Override
    public String toString() {
        return "TupleElementNode{} " + super.toString();
    }
}
