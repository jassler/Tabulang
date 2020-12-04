package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.interpretTest.Interpretation;

public class ImplNode extends BinaryNode{
    public ImplNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Boolean left = (Boolean) getLeftNode().evaluateNode(interpretation);
        Boolean right = (Boolean) getRightNode().evaluateNode(interpretation);
        if(left == false && right == true){
            return false;
        } else {
            return true;
        }

    }
}
