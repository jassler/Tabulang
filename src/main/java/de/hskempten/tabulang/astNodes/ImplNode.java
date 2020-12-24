package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.interpretTest.Interpretation;

public class ImplNode extends BinaryPredicateNode{
    public ImplNode(PredicateNode leftNode, PredicateNode rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        if(!getBooleanValue(getLeftNode(), interpretation) && getBooleanValue(getRightNode(), interpretation)){
            return false;
        } else {
            return true;
        }
    }
}
