package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.datatypes.exceptions.TupleNameNotFoundException;
import de.hskempten.tabulang.interpretTest.Interpretation;

public class TupleElementNode extends BinaryNode{


    public TupleElementNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object tuple = getLeftNode().evaluateNode(interpretation);
        if(tuple instanceof Tuple){
            Object columnIdentifier = getRightNode().evaluateNode(interpretation);
            if(columnIdentifier instanceof String){
                if(((Tuple<?>) tuple).getNames().getNames().contains(columnIdentifier)){
                    return ((Tuple) tuple).get((String) columnIdentifier);
                } else {
                    throw new TupleNameNotFoundException((String)columnIdentifier);
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
