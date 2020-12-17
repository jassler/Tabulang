package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.interpretTest.Interpretation;

public class ImplNode extends BinaryPredicateNode{
    public ImplNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Boolean left = getBooleanValue(getLeftNode(), interpretation);
        Boolean right = getBooleanValue(getRightNode(), interpretation);
        if(left == false && right == true){
            return false;
        } else {
            return true;
        }

    }
}
