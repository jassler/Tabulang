package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.interpretTest.Interpretation;

public class IffNode extends BinaryPredicateNode{
    public IffNode(PredicateNode leftNode, PredicateNode rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        if(getBooleanValue(getLeftNode(), interpretation) == getBooleanValue(getRightNode(), interpretation)){
            return true;
        } else {
            return false;
        }
    }
}
