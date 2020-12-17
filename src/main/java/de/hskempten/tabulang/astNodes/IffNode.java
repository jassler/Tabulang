package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.interpretTest.Interpretation;

public class IffNode extends BinaryPredicateNode{
    public IffNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Boolean left = getBooleanValue(getLeftNode(), interpretation);
        Boolean right = getBooleanValue(getRightNode(), interpretation);
        if(left == right){
            return true;
        } else {
            return false;
        }
    }
}
